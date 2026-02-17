package mx.florinda.cardapio;

import com.google.gson.Gson;

import java.io.*;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorItensCardapioComSocket {

    private static final Logger logger = Logger.getLogger(ServidorItensCardapioComSocket.class.getName());

    private static final Database database = new SQLDatabase();

    public static void main(String[] args) throws Exception {

        try (ExecutorService executorService = Executors.newFixedThreadPool(50)) {

            try (ServerSocket serverSocket = new ServerSocket(8000)) {
                logger.info("Subiu servidor!");

                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    executorService.execute(() -> trataRequisicao(clientSocket));
                }

            }

        }

    }

    private static void trataRequisicao(Socket clientSocket) {

        try (clientSocket) {
            InputStream clientIS = clientSocket.getInputStream();

            StringBuilder requestBuilder = new StringBuilder();

            int data;
            do {
                data = clientIS.read();
                requestBuilder.append((char) data);
            } while (clientIS.available() > 0);

            String request = requestBuilder.toString();
            logger.finest("----------------------------");
            logger.finest(request);
            logger.fine("\n\nChegou um novo request");

            String[] requestChunks = request.split("\r\n\r\n"); // \r CR \n LF (CRLF)
            String requestLineAndHeaders = requestChunks[0];
            String[] requestLineAndHeadersChunks = requestLineAndHeaders.split("\r\n");
            String requestLine = requestLineAndHeadersChunks[0];
            String[] requestLineChunks = requestLine.split(" ");
            // method (GET/POST)
            String method = requestLineChunks[0];
            // uri
            String requestUri = requestLineChunks[1];
            String httpVersion = requestLineChunks[2];

            logger.finer(() -> "Method: " + method);
            logger.finer(() -> "Request URI: " + requestUri);
            logger.finer(() -> "HTTP Version: " + httpVersion);

            Thread.sleep(250);

            OutputStream clientOS = clientSocket.getOutputStream();
            PrintStream clientOut = new PrintStream(clientOS);

            try {
                if (method.equals("GET") && requestUri.equals("/itensCardapio.json")) {
                    logger.fine("Chamou arquivo itensCardapio.json");
                    Path path = Path.of("itensCardapio.json");
                    String json = Files.readString(path);

                    clientOut.println("HTTP/1.1 200 OK");
                    clientOut.println("Content-type: application/json; charset=UTF-8");
                    clientOut.println();
                    clientOut.println(json);
                } else if (method.equals("GET") && requestUri.equals("/itens-cardapio")) {
                    logger.fine("Chamou listagem de itens de cardápio");
                    List<ItemCardapio> listaItensCardapio = database.listaDeItensCardapio();

                    String mediaType = "application/json";
                    for (int i = 1; i < requestLineAndHeadersChunks.length; i++) {
                        String header = requestLineAndHeadersChunks[i];
                        logger.fine(header);
                        if (header.contains("Accept")) {
                            mediaType = header.replace("Accept: ", "");
                            logger.info("Media type: " + mediaType);
                        }
                    }

                    byte[] body;

                    if ("application/x-java-serialized-object".equals(mediaType)) {
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        var oos = new ObjectOutputStream(baos);
                        oos.writeObject(listaItensCardapio);
                        body = baos.toByteArray();
                    } else {
                        Gson gson = new Gson();
                        String json = gson.toJson(listaItensCardapio);

                        body = json.getBytes(StandardCharsets.UTF_8);
                    }


                    clientOS.write("HTTP/1.1 200 OK\r\n".getBytes());
                    clientOS.write(("Content-type: " + mediaType + "; charset=UTF-8\r\n\r\n").getBytes(StandardCharsets.UTF_8));
                    clientOS.write(body);
                } else if (method.equals("GET") && requestUri.matches("/itens-cardapio/[0-9]+")) {
                    logger.fine("Chamou listagem de itens de cardápio por id");

                    String[] uriChunks = requestUri.split("/");
                    Long id = Long.parseLong(uriChunks[2]);

                    ItemCardapio itemCardapio = database.itemCardapioPorId(id).orElse(null);

                    if (itemCardapio == null) {
                        clientOut.println("HTTP/1.1 404 Not Found");
                        return;
                    }

                    Gson gson = new Gson();
                    String json = gson.toJson(itemCardapio);

                    clientOut.println("HTTP/1.1 200 OK");
                    clientOut.println("Content-type: application/json; charset=UTF-8");
                    clientOut.println();
                    clientOut.println(json);
                } else if (method.equals("GET") && requestUri.equals("/itens-cardapio/total")) {
                    logger.fine("Chamou total de itens de cardápio");
                    List<ItemCardapio> listaItensCardapio = database.listaDeItensCardapio();
                    int total = listaItensCardapio.size();

                    clientOut.println("HTTP/1.1 200 OK");
                    clientOut.println("Content-type: application/json; charset=UTF-8");
                    clientOut.println();
                    clientOut.println(total);
                } else if (method.equals("POST") && requestUri.equals("/itens-cardapio")) {
                    // curl -v -X POST -d '{"id":12,"nome":"Item 12","descricao":"Item 12.","categoria":"BEBIDAS","preco":2.99}' -H 'Content-Type: application/json' http://localhost:8000/itens-cardapio
                    logger.fine("Chamou adição de item de cardápio");

                    if (requestChunks.length == 1) {
                        clientOut.println("HTTP/1.1 400 Bad Request");
                        return;
                    }

                    String body = requestChunks[1];

                    Gson gson = new Gson();
                    ItemCardapio novoItemCardapio = gson.fromJson(body, ItemCardapio.class);

                    logger.fine(novoItemCardapio.toString());

                    database.adicionaItemCardapio(novoItemCardapio);

                    clientOut.println("HTTP/1.1 201 Created");
                } else if (method.equals("DELETE") && requestUri.matches("/itens-cardapio/[0-9]+")) {
                    logger.fine("Chamou a remoção de itens de cardápio por id");

                    String[] uriChunks = requestUri.split("/");
                    Long id = Long.parseLong(uriChunks[2]);

                    database.removeItemCardapio(id);

                    clientOut.println("HTTP/1.1 200 OK");
                } else if (method.equals("PATCH") && requestUri.matches("/itens-cardapio/[0-9]+")) {
                    // curl -v -X PATCH -d '{"preco":23.99}' -H 'Content-Type: application/json' http://localhost:8000/itens-cardapio/10
                    logger.fine("Chamou a atualização de itens de cardápio por id");

                    String[] uriChunks = requestUri.split("/");
                    Long id = Long.parseLong(uriChunks[2]);

                    if (requestChunks.length == 1) {
                        clientOut.println("HTTP/1.1 400 Bad Request");
                        return;
                    }

                    String body = requestChunks[1];

                    Gson gson = new Gson();
                    Map mapa = gson.fromJson(body, Map.class);
                    String novoPreco = mapa.get("preco").toString();

                    database.alterarPrecoItemCardapio(id, new BigDecimal(novoPreco));

                    clientOut.println("HTTP/1.1 200 OK");
                } else if ("GET".equals(method) && ("/".equals(requestUri) || "/en".equals(requestUri))) {

                    List<ItemCardapio> listaDeItensCardapio = database.listaDeItensCardapio();

                    Locale locale = requestUri.equals("/en") ? Locale.US : Locale.of("pt", "BR");
                    NumberFormat formatadorMoeda = NumberFormat.getCurrencyInstance(locale);
                    ResourceBundle mensagens = ResourceBundle.getBundle("mensagens", locale);

                    StringBuilder htmlTodosItens = new StringBuilder();
                    for (ItemCardapio item : listaDeItensCardapio) {

                        String htmlPrecoItem;
                        if (item.precoPromocional() == null) {
                            htmlPrecoItem = "<strong> " + formatadorMoeda.format(item.preco()) + "</strong>";
                        } else {
                            htmlPrecoItem = "<mark>Em promoção</mark> <strong> " + formatadorMoeda.format(item.preco()) +"</strong> <s>"+formatadorMoeda.format(item.precoPromocional())+"</s>";
                        }

                        String categoria = mensagens.getString("categoria.cardapio." + item.categoria().name().toLowerCase());

                        String htmlItem = """
                                          <article>
                                            <kbd>%s</kbd>
                                            <h3>%s</h3>
                                            <p>%s</p>
                                            %s
                                          </article>
                                          """.formatted(categoria, item.nome(), item.descricao(), htmlPrecoItem);

                        htmlTodosItens.append(htmlItem);
                    }

                    DateTimeFormatter formatterDataHora = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG).withLocale(locale);
                    DateTimeFormatter formatterMesAno = DateTimeFormatter.ofPattern("MMMM/yyyy").withLocale(locale);


                    String html = """
                            <!DOCTYPE html>
                            <html lang="en">
                            <head>
                                <meta charset="UTF-8">
                                <title>Florinda Eats - Cardápio</title>
                                <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@2.1.1/css/pico.min.css">
                            </head>
                                <body>
                                    <header class="container">
                                        <hgroup>
                                            <h1>Florinda Eats</h1>
                                            <p>O sabor da Vila direto pra você</p>
                                        </hgroup>
                                    </header>
                                    <main class="container">
                                        <h2>Cardápio</h2>
                                        %s
                                    </main>
                                    <footer class="container">
                                        <p><small><em>Preços de acordo com %s</em></small></p>
                                        <p><strong>Florinda Eats</strong> Todos os direitos reservados - %s</p>
                                    </footer>
                                </body>
                             </html>
                            """.formatted(htmlTodosItens, formatterDataHora.format(ZonedDateTime.now()), formatterMesAno.format(YearMonth.now()));
                    clientOut.print("HTTP/1.1 200 OK\r\n");
                    clientOut.print("Content-type: text/html; charset=UTF-8\r\n\r\n");
                    clientOut.print(html);
                    clientOut.print("\r\n");
                } else {
                    logger.warning(() -> "URI não encontrada: " + requestUri);
                    clientOut.println("HTTP/1.1 404 Not Found");
                }
            } catch (Exception e) {
                logger.log(Level.SEVERE, e, () -> "Erro ao tratar " + method + " " + requestUri);
                clientOut.println("HTTP/1.1 500 Internal Server Error");
                clientOut.println();
                clientOut.println(e.getMessage());
            }

        } catch (Exception e) {
            //logger.severe("Erro no servidor.");
            logger.log(Level.SEVERE, "Erro no servidor", e);
            throw new RuntimeException(e);
        }

    }

}

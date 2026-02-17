package mx.florinda.cardapio;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
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

                    Gson gson = new Gson();
                    String json = gson.toJson(listaItensCardapio);

                    clientOut.println("HTTP/1.1 200 OK");
                    clientOut.println("Content-type: application/json; charset=UTF-8");
                    clientOut.println();
                    clientOut.println(json);
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

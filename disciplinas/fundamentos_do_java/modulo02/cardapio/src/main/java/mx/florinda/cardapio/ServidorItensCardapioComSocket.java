package mx.florinda.cardapio;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static mx.florinda.cardapio.ItemCardapio.CategoriaCardapio.BEBIDAS;

public class ServidorItensCardapioComSocket {

    public static void main(String[] args) throws Exception {

        try (ExecutorService executorService = Executors.newFixedThreadPool(50)) {

            try (ServerSocket serverSocket = new ServerSocket(8000)) {
                System.out.println("Subiu servidor!");

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
            System.out.println(request);

            Thread.sleep(250);

            String urlGetItensCardapio = "GET /itens-cardapio HTTP/1.1";
            String urlGetItensCardapioTotal = "GET /itens-cardapio/total HTTP/1.1";
            String urlPostItensCardapio = "POST /itens-cardapio HTTP/1.1";

            if (request.contains(urlGetItensCardapio)) {
                listarItens(clientSocket);
            } else if (request.contains(urlGetItensCardapioTotal)) {
                totalItensLista(clientSocket);
            } else if (request.contains(urlPostItensCardapio)) {
                adicionarItem(clientSocket);
            } 

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static void adicionarItem(Socket clientSocket) throws IOException {
        ItemCardapio itemCardapio = new ItemCardapio(15L, "Refresco do Quico", "Refresco mais refrescante", BEBIDAS, new BigDecimal("2.99"), null);
        String json = recuperarJson();
        Gson gson = new Gson();
        var listaItensCardapio = gson.fromJson(json, List.class);
        listaItensCardapio.add(itemCardapio);
        json = gson.toJson(listaItensCardapio);
        String itemJsonRetorno = new Gson().toJson(itemCardapio);
        Path path = Path.of("itensCardapio.json");
        Files.writeString(path, json);
        retornoClientOut(clientSocket, itemJsonRetorno);
    }

    private static void listarItens(Socket clientSocket) throws IOException {
        String json = recuperarJson();
        retornoClientOut(clientSocket, json);
    }

    private static String recuperarJson() throws IOException {
        Path path = Path.of("itensCardapio.json");
        return Files.readString(path);
    }


    private static void totalItensLista(Socket clientSocket) throws IOException {
        String json = recuperarJson();
        Gson gson = new Gson();
        var listaItensCardapio = gson.fromJson(json, List.class);
        int totalItens = listaItensCardapio.size();
        String jsonTotal = "{\"totalItens\": " + totalItens + "}";
        retornoClientOut(clientSocket, jsonTotal);
    }

    private static void retornoClientOut(Socket clientSocket, String json) throws IOException {
        OutputStream clientOS = clientSocket.getOutputStream();
        PrintStream clientOut = new PrintStream(clientOS);
        clientOut.println("HTTP/1.1 200 OK");
        clientOut.println("Content-type: application/json; charset=UTF-8");
        clientOut.println();
        clientOut.println(json);
    }


}

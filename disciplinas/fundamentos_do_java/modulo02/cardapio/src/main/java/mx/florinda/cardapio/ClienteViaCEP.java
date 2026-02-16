package mx.florinda.cardapio;

import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class ClienteViaCEP {

    public static void main(String[] args) throws Exception {

        URI uri = URI.create("https://viacep.com.br/ws/01001000/json/"); // Java 1.4

        try (HttpClient httpClient = HttpClient.newHttpClient()) { // HttpClient - Java 11

            HttpRequest httpRequest = HttpRequest.newBuilder(uri).build();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest,
                                                                HttpResponse.BodyHandlers.ofString());

            int statusCode = httpResponse.statusCode();
            String body = httpResponse.body();

            System.out.println(statusCode);
            System.out.println(body);
        }

        /*
            URL url = new URL("https://viacep.com.br/ws/01001000/json/"); // Java 1.0
            try (Scanner scanner = new Scanner(url.openStream())) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    System.out.println(line);
                }
            }
        */

    }

}

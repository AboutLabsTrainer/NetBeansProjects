package de.ibb.netzwerk.tag10;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Ein einzelner GET-Request mit {@code java.net.http}: Statuscode und Antworttext ausgeben.
 */
public class SimpleHttpGetExample {

    public static void main(String[] args) {
        String url = "https://jsonplaceholder.typicode.com/posts/1";
        System.out.println("Sende GET-Request an: " + url);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status: " + response.statusCode());

            String body = response.body();
            System.out.println("Body:");
            System.out.println(body);
        } catch (IOException e) {
            System.out.println("I/O-Fehler beim HTTP-Request: " + e.getMessage());
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("HTTP-Request unterbrochen: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}


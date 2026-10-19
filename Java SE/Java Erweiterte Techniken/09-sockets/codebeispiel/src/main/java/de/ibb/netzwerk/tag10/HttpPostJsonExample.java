package de.ibb.netzwerk.tag10;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Schickt JSON per POST und liest eine Id aus der Antwort, ohne eine JSON-Bibliothek einzubinden.
 */
public class HttpPostJsonExample {

    private static final Pattern ID_PATTERN = Pattern.compile("\"id\"\\s*:\\s*(\\d+)");

    public static void main(String[] args) {
        String url = "https://jsonplaceholder.typicode.com/posts";
        System.out.println("Sende POST-Request mit JSON an: " + url);

        HttpClient client = NetzwerkUtils.createDefaultHttpClient();

        String jsonBody = """
                {
                  "title": "Mein Post",
                  "body": "Inhalt hier",
                  "userId": 1
                }
                """;

        HttpRequest request = NetzwerkUtils.defaultRequestBuilder(url)
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(jsonBody))
                .build();

        try {
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            System.out.println("Status: " + response.statusCode());
            String body = response.body();
            System.out.println("Body:");
            System.out.println(body);

            extractAndPrintId(body);
        } catch (IOException e) {
            System.out.println("I/O-Fehler beim HTTP-POST: " + e.getMessage());
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("HTTP-POST unterbrochen: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    private static void extractAndPrintId(String json) {
        Matcher matcher = ID_PATTERN.matcher(json);
        if (matcher.find()) {
            String id = matcher.group(1);
            System.out.println("Extrahierte ID aus JSON: " + id);
        } else {
            System.out.println("Keine ID im JSON gefunden.");
        }
    }
}


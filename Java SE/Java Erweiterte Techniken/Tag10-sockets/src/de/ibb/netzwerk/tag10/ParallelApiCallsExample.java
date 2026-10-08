package de.ibb.netzwerk.tag10;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Startet mehrere GET-Requests gleichzeitig über {@code sendAsync}, mit Timeout pro Aufruf und für alle zusammen.
 */
public class ParallelApiCallsExample {

    public static void main(String[] args) {
        HttpClient client = NetzwerkUtils.createDefaultHttpClient();

        List<String> urls = List.of(
                "https://jsonplaceholder.typicode.com/posts/1",
                "https://jsonplaceholder.typicode.com/posts/2",
                "https://jsonplaceholder.typicode.com/posts/3"
        );

        System.out.println("Starte parallele Requests zu:");
        urls.forEach(System.out::println);

        List<CompletableFuture<Void>> futures = urls.stream()
                .map(url -> sendAsyncWithLogging(client, url))
                .toList();

        CompletableFuture<Void> all = CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));

        try {
            all.get(60, TimeUnit.SECONDS);
            System.out.println("Alle Requests abgeschlossen.");
        } catch (TimeoutException e) {
            System.out.println("Gesamt-Timeout überschritten: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Fehler beim Warten auf parallele Requests: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static CompletableFuture<Void> sendAsyncWithLogging(HttpClient client, String url) {
        HttpRequest request = NetzwerkUtils.defaultRequestBuilder(url)
                .GET()
                .build();

        System.out.println("Sende asynchronen Request an: " + url);

        return client.sendAsync(request, BodyHandlers.ofString())
                .orTimeout(30, TimeUnit.SECONDS)
                .thenAccept(response -> {
                    String bodyShort = NetzwerkUtils.shorten(response.body(), 50);
                    System.out.println("[" + url + "] Status: " + response.statusCode());
                    System.out.println("[" + url + "] Body (Auszug): " + bodyShort);
                })
                .exceptionally(ex -> {
                    Throwable cause = ex instanceof CompletionException ? ex.getCause() : ex;
                    if (cause instanceof TimeoutException) {
                        System.out.println("[" + url + "] Request-Timeout nach 30 Sekunden.");
                    } else if (cause instanceof IOException) {
                        System.out.println("[" + url + "] I/O-Fehler: " + cause.getMessage());
                    } else {
                        System.out.println("[" + url + "] Fehler: " + cause.getClass().getSimpleName()
                                + " - " + cause.getMessage());
                    }
                    return null;
                });
    }
}


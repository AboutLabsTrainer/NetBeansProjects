package de.ibb.netzwerk.tag10;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;

/**
 * Gemeinsame HttpClient-Einstellungen, damit die HTTP-Beispiele nicht jedes Mal denselben Aufbau wiederholen.
 */
public final class NetzwerkUtils {

    private NetzwerkUtils() {
        // Utility class
    }

    public static HttpClient createDefaultHttpClient() {
        return HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(10))
                .build();
    }

    public static HttpRequest.Builder defaultRequestBuilder(String url) {
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(30));
    }

    public static String shorten(String text, int maxLength) {
        if (text == null) {
            return "";
        }
        if (text.length() <= maxLength) {
            return text;
        }
        return text.substring(0, maxLength) + "...";
    }
}


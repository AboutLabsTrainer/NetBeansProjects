package de.ibb.boot.ak.services;

public final class DownstreamPaths {
    private DownstreamPaths() {
    }

    public static String users(String baseUrl) {
        return strip(baseUrl) + "/api/users";
    }

    public static String orders(String baseUrl) {
        return strip(baseUrl) + "/api/orders";
    }

    private static String strip(String baseUrl) {
        if (baseUrl.endsWith("/")) {
            return baseUrl.substring(0, baseUrl.length() - 1);
        }
        return baseUrl;
    }
}

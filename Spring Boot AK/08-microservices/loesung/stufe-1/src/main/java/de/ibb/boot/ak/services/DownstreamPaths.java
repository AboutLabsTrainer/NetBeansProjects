package de.ibb.boot.ak.services;

public final class DownstreamPaths {
    private DownstreamPaths() {
    }

    public static String users(String baseUrl) {
        return strip(baseUrl) + "/api/users";
    }

    private static String strip(String baseUrl) {
        if (baseUrl.endsWith("/")) {
            return baseUrl.substring(0, baseUrl.length() - 1);
        }
        return baseUrl;
    }
}

package de.ibb.integration.service;

public final class ServiceRegeln {

    private ServiceRegeln() {
    }

    public static String normalisiereEmail(String email) {
        if (email == null) {
            return null;
        }
        return email.toLowerCase().trim();
    }

    public static boolean alleLaden(String suchbegriff) {
        return suchbegriff == null || suchbegriff.trim().isEmpty();
    }
}

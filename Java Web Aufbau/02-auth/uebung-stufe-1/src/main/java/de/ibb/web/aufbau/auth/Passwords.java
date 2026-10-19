package de.ibb.web.aufbau.auth;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Passwords {
    private Passwords() {}

    public static String sha256Hex(String raw) {
        // TODO: SHA-256 über UTF-8, Hex mit führender Null, klein.
        throw new UnsupportedOperationException("TODO");
    }
}

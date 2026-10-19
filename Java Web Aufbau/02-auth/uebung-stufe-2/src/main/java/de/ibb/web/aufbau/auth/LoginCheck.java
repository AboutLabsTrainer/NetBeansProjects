package de.ibb.web.aufbau.auth;

public final class LoginCheck {
    private LoginCheck() {}

    public static boolean accept(String email, String password, String storedHash) {
        // TODO: E-Mail mit genau einem @ nicht am Rand, Passwort als SHA-256 gegen den gespeicherten Hash.
        throw new UnsupportedOperationException("TODO");
    }
}

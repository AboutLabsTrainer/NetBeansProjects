package de.ibb.web.aufbau.filter;

public final class Guard {
    public enum Decision { CONTINUE, REDIRECT_LOGIN }

    private Guard() {}

    public static Decision decide(String path, boolean userPresent) {
        // TODO: /admin und /api verlangen einen User.
        throw new UnsupportedOperationException("TODO");
    }
}

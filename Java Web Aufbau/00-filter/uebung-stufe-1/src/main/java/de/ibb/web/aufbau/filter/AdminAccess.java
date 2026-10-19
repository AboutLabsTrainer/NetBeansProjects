package de.ibb.web.aufbau.filter;

public final class AdminAccess {
    public enum Decision { CONTINUE, REDIRECT_LOGIN }

    private AdminAccess() {}

    public static Decision decide(String path, boolean userPresent) {
        // TODO: /admin ohne User ergibt REDIRECT_LOGIN, sonst CONTINUE.
        throw new UnsupportedOperationException("TODO");
    }
}

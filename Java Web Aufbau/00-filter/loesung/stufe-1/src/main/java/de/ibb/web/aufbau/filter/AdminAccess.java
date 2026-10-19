package de.ibb.web.aufbau.filter;

public final class AdminAccess {
    public enum Decision { CONTINUE, REDIRECT_LOGIN }

    private AdminAccess() {}

    public static Decision decide(String path, boolean userPresent) {
        if (path != null && path.startsWith("/admin") && !userPresent) {
            return Decision.REDIRECT_LOGIN;
        }
        return Decision.CONTINUE;
    }
}

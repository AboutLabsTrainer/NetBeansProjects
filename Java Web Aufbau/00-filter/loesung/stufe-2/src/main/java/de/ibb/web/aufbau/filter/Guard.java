package de.ibb.web.aufbau.filter;

public final class Guard {
    public enum Decision { CONTINUE, REDIRECT_LOGIN }

    private Guard() {}

    public static Decision decide(String path, boolean userPresent) {
        boolean guarded = path != null && (path.startsWith("/admin") || path.startsWith("/api"));
        if (guarded && !userPresent) {
            return Decision.REDIRECT_LOGIN;
        }
        return Decision.CONTINUE;
    }
}

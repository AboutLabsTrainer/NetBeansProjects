package de.ibb.web.aufbau.security;

public final class RoleGate {
    private RoleGate() {}

    public static boolean allows(String area, String role) {
        // TODO: admin darf admin, manager und user. Die anderen Rollen nur die eigene Fläche.
        throw new UnsupportedOperationException("TODO");
    }
}

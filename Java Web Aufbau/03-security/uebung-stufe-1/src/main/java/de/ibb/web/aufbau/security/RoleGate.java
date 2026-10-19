package de.ibb.web.aufbau.security;

public final class RoleGate {
    private RoleGate() {}

    public static boolean allows(String area, String role) {
        // TODO: area und role müssen admin, manager oder user sein und übereinstimmen.
        throw new UnsupportedOperationException("TODO");
    }
}

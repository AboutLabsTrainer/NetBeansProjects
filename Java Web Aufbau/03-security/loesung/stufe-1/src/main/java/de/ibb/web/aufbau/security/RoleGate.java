package de.ibb.web.aufbau.security;

public final class RoleGate {
    private RoleGate() {}

    public static boolean allows(String area, String role) {
        if (!known(area) || !known(role)) {
            return false;
        }
        return area.equals(role);
    }

    private static boolean known(String value) {
        return "admin".equals(value) || "manager".equals(value) || "user".equals(value);
    }
}

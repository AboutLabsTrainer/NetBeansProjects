package de.ibb.boot.ak.security;

import java.util.Set;

public final class AccessPolicy {
    private AccessPolicy() {
    }

    public static boolean canOpenAdmin(Set<String> roles) {
        return roles.contains("ADMIN");
    }
}

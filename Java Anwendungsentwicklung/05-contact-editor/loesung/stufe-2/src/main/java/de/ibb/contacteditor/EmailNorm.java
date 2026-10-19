package de.ibb.contacteditor;

public final class EmailNorm {

    private EmailNorm() {
    }

    public static String normalisiere(String email) {
        if (email == null) {
            return null;
        }
        return email.trim().toLowerCase();
    }
}

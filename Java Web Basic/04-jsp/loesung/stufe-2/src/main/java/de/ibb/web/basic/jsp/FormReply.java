package de.ibb.web.basic.jsp;

public final class FormReply {
    private FormReply() {}

    public static String thanks(String name, String comment) {
        if (comment == null || comment.isBlank()) {
            throw new IllegalArgumentException("comment");
        }
        if (name != null && name.trim().length() > 40) {
            throw new IllegalArgumentException("name");
        }
        String who = (name == null || name.isBlank()) ? "Gast" : name.trim();
        return "Danke, " + who;
    }
}

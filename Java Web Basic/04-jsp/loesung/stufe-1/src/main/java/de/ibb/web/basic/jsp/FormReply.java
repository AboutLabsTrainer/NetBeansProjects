package de.ibb.web.basic.jsp;

public final class FormReply {
    private FormReply() {}

    public static String thanks(String name) {
        if (name == null || name.isBlank()) {
            return "Danke, Gast";
        }
        return "Danke, " + name.trim();
    }
}

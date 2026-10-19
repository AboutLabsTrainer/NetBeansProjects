package de.ibb.web.basic.mvc;

public final class BlogView {
    private BlogView() {}

    public static String select(String id) {
        if (id == null || id.isBlank()) {
            return "blog-list";
        }
        try {
            if (Integer.parseInt(id.trim()) > 0) {
                return "blog-post";
            }
        } catch (NumberFormatException ex) {
            return "error";
        }
        return "error";
    }
}

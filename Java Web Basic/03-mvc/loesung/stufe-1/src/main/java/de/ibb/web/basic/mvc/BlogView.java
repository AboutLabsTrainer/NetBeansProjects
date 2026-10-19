package de.ibb.web.basic.mvc;

public final class BlogView {
    private BlogView() {}

    public static String select(String id) {
        if (id == null || id.isBlank()) {
            return "blog-list";
        }
        return "blog-post";
    }
}

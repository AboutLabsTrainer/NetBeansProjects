package de.ibb.web.basic.mvc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BlogViewTest {
    @Test
    void choosesListOrPost() {
        assertEquals("blog-list", BlogView.select(null));
        assertEquals("blog-list", BlogView.select("  "));
        assertEquals("blog-post", BlogView.select("3"));
    }
}

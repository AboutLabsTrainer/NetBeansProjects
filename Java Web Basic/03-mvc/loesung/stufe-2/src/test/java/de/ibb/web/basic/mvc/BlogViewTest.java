package de.ibb.web.basic.mvc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BlogViewTest {
    @Test
    void validatesTheId() {
        assertEquals("blog-list", BlogView.select(" "));
        assertEquals("blog-post", BlogView.select("2"));
        assertEquals("error", BlogView.select("0"));
        assertEquals("error", BlogView.select("-1"));
        assertEquals("error", BlogView.select("x"));
    }
}

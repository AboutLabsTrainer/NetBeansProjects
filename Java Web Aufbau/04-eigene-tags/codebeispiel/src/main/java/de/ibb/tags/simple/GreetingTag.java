package de.ibb.tags.simple;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * SimpleTagSupport ohne Body. doTag() schreibt eine Anrede aus name und time.
 */
public class GreetingTag extends SimpleTagSupport {

    private String name, time;

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public void doTag() throws JspException, IOException {
        String t = time == null ? "" : time.toLowerCase();
        String prefix = switch (t) {
            case "morning" ->
                "Good morning";
            case "afternoon" ->
                "Good afternoon";
            case "evening" ->
                "Good evening";
            default ->
                "Hello";
        };
        String who = (name == null || name.isBlank()) ? "there" : name.trim();
        getJspContext().getOut().write(prefix + ", " + who + "!");
    }
}

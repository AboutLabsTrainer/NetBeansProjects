package de.ibb.tags.classic;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Klassisches TagSupport-Tag ohne Body. doStartTag() schreibt die Anrede und liefert SKIP_BODY.
 */
public class GreetingClassicTag extends TagSupport {

    private String name, time;

    public void setName(String n) {
        this.name = n;
    }

    public void setTime(String t) {
        this.time = t;
    }

    @Override
    public int doStartTag() throws JspException {
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
        try {
            pageContext.getOut().write(prefix + ", " + who + "!");
        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }
}

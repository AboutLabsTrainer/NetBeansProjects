package de.ibb.tags.simple;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * SimpleTagSupport ohne Body. doTag() schreibt ein farbiges Badge.
 */
public class BadgeTag extends SimpleTagSupport {

    private String value;
    private String color = "secondary";

    public void setValue(String value) {
        this.value = value;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public void doTag() throws JspException, IOException {
        String v = value == null ? "" : value;
        String c = (color == null || color.isBlank()) ? "secondary" : color.trim();
        String html = "<span class='badge badge-" + c + "'>" + escape(v) + "</span>";
        getJspContext().getOut().write(html);
    }

    private String escape(String s) {
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;").replace("'", "&#39;");
    }
}

package de.ibb.tags.classic;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Klassisches TagSupport-Tag ohne Body. doStartTag() schreibt das Badge und überspringt den Rumpf.
 */
public class BadgeClassicTag extends TagSupport {

    private String value;
    private String color = "secondary";

    public void setValue(String v) {
        this.value = v;
    }

    public void setColor(String c) {
        this.color = c;
    }

    @Override
    public int doStartTag() throws JspException {
        String v = value == null ? "" : value;
        String c = (color == null || color.isBlank()) ? "secondary" : color.trim();
        String html = "<span class='badge badge-" + c + "'>" + escape(v) + "</span>";
        try {
            pageContext.getOut().write(html);
        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }

    private String escape(String s) {
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;").replace("'", "&#39;");
    }
}

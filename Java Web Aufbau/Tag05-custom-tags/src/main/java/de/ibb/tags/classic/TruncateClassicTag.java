package de.ibb.tags.classic;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.BodyTagSupport;
import jakarta.servlet.jsp.tagext.BodyContent;
import java.io.IOException;
import jakarta.servlet.jsp.JspWriter;

/**
 * BodyTagSupport. Nach dem Body wird der gepufferte Rumpf auf maxLength gekürzt und ausgegeben.
 */
public class TruncateClassicTag extends BodyTagSupport {

    private int maxLength = 50;

    public void setMaxLength(int m) {
        this.maxLength = m;
    }

    @Override
    public int doStartTag() throws JspException {
        return EVAL_BODY_BUFFERED;
    }

    @Override
    public int doAfterBody() throws JspException {
        BodyContent bc = getBodyContent();
        String text = bc != null ? bc.getString() : "";
        String out = text;
        if (text != null && text.length() > maxLength) {
            out = (maxLength <= 1) ? "…" : text.substring(0, Math.max(0, maxLength - 1)) + "…";
        }
        try {
            JspWriter w = bc.getEnclosingWriter();
            w.write(out);
        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}

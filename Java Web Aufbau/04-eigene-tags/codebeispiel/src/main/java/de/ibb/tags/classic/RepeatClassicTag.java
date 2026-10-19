package de.ibb.tags.classic;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.BodyTagSupport;
import jakarta.servlet.jsp.tagext.BodyContent;
import java.io.IOException;
import jakarta.servlet.jsp.JspWriter;

/**
 * BodyTagSupport-Schleife. Der gepufferte Rumpf wird erneut ausgewertet, bis der Zähler times erreicht.
 */
public class RepeatClassicTag extends BodyTagSupport {

    private int times = 1;
    private int count = 0;

    public void setTimes(int t) {
        this.times = t;
    }

    @Override
    public int doStartTag() throws JspException {
        if (times < 0) {
            times = 0;
        }
        count = 0;
        return EVAL_BODY_BUFFERED;
    }

    @Override
    public int doAfterBody() throws JspException {
        BodyContent bc = getBodyContent();
        String body = bc != null ? bc.getString() : "";
        try {
            JspWriter out = bc.getEnclosingWriter();
            out.write(body);
        } catch (IOException e) {
            throw new JspException(e);
        }
        count++;
        if (count < times) {
            if (bc != null) {
                bc.clearBody();
            }
            return EVAL_BODY_AGAIN;
        } else {
            return SKIP_BODY;
        }
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}

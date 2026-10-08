package de.ibb.tags.simple;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import jakarta.servlet.jsp.tagext.JspFragment;
import java.io.IOException;
import java.io.StringWriter;

/**
 * SimpleTagSupport mit Body. Der Rumpf wird gepuffert und auf maxLength gekürzt.
 */
public class TruncateTag extends SimpleTagSupport {

    private int maxLength = 50;

    public void setMaxLength(int m) {
        this.maxLength = m;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspFragment body = getJspBody();
        if (body == null) {
            return;
        }
        String text;
        try (StringWriter sw = new StringWriter()) {
            body.invoke(sw);
            text = sw.toString();
        }
        String out = text;
        if (text != null && text.length() > maxLength) {
            out = (maxLength <= 1) ? "…" : text.substring(0, Math.max(0, maxLength - 1)) + "…";
        }
        getJspContext().getOut().write(out);
    }
}

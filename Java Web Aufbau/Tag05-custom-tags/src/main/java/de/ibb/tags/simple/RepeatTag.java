package de.ibb.tags.simple;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import jakarta.servlet.jsp.tagext.JspFragment;
import java.io.IOException;

/**
 * SimpleTagSupport mit Body. Der Rumpf wird times-mal ausgegeben.
 */
public class RepeatTag extends SimpleTagSupport {

    private int times = 1;

    public void setTimes(int t) {
        this.times = t;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspFragment body = getJspBody();
        if (body == null) {
            return;
        }
        if (times < 0) {
            times = 0;
        }
        for (int i = 0; i < times; i++) {
            body.invoke(getJspContext().getOut());
        }
    }
}

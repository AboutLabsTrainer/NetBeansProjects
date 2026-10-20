package com.demo.listener;

import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

/**
 * Session attribute listener: logs when attributes are added, replaced, or
 * removed from an {@link jakarta.servlet.http.HttpSession}.
 */
public class AppSessionAttributeListener implements HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        System.out.println("[AppSessionAttributeListener] attributeAdded -- session="
                + event.getSession().getId() + ", name=" + event.getName()
                + ", value=" + event.getValue());
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        System.out.println("[AppSessionAttributeListener] attributeRemoved -- session="
                + event.getSession().getId() + ", name=" + event.getName()
                + ", value=" + event.getValue());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        System.out.println("[AppSessionAttributeListener] attributeReplaced -- session="
                + event.getSession().getId() + ", name=" + event.getName()
                + ", value=" + event.getValue());
    }
}
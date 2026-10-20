package com.demo.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Session listener: reacts to session creation and destruction.
 * Updates the active-session counter stored in the ServletContext.
 */
public class AppSessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("[AppSessionListener] sessionCreated -- id="
                + se.getSession().getId());
        incrementSessionCount(se.getSession().getServletContext());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("[AppSessionListener] sessionDestroyed -- id="
                + se.getSession().getId());
        decrementSessionCount(se.getSession().getServletContext());
    }

    private void incrementSessionCount(ServletContext ctx) {
        Object attr = ctx.getAttribute(AppContextListener.ATTR_SESSION_COUNT);
        if (attr instanceof AtomicInteger) {
            ((AtomicInteger) attr).incrementAndGet();
        }
    }

    private void decrementSessionCount(ServletContext ctx) {
        Object attr = ctx.getAttribute(AppContextListener.ATTR_SESSION_COUNT);
        if (attr instanceof AtomicInteger) {
            ((AtomicInteger) attr).decrementAndGet();
        }
    }
}
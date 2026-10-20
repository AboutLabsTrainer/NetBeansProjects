package com.demo.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Context listener: reacts to web application startup and shutdown.
 *
 * Stores the application start time and the request/session counters in the
 * {@link ServletContext} so that servlets and JSPs can read them (app scope).
 */
public class AppContextListener implements ServletContextListener {

    public static final String ATTR_START_TIME   = "appStartTime";
    public static final String ATTR_REQUEST_COUNT = "appRequestCount";
    public static final String ATTR_SESSION_COUNT = "appSessionCount";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();
        ctx.setAttribute(ATTR_START_TIME, Instant.now());
        ctx.setAttribute(ATTR_REQUEST_COUNT, new AtomicInteger(0));
        ctx.setAttribute(ATTR_SESSION_COUNT, new AtomicInteger(0));
        System.out.println("[AppContextListener] contextInitialized -- application started at "
                + Instant.now());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();
        System.out.println("[AppContextListener] contextDestroyed -- application shutting down");
        ctx.removeAttribute(ATTR_START_TIME);
        ctx.removeAttribute(ATTR_REQUEST_COUNT);
        ctx.removeAttribute(ATTR_SESSION_COUNT);
    }
}
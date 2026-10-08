package de.ibb.examples.tag3.listeners;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Beobachtet Start der Anwendung, Sessions und eingehende Requests.
 */
@WebListener
public class AppLifecycleListener implements ServletContextListener, HttpSessionListener, ServletRequestListener {

    private final AtomicInteger activeSessions = new AtomicInteger();
    private final AtomicInteger requestCount = new AtomicInteger();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("app.startTime", Instant.now());
        sce.getServletContext().setAttribute("metrics.activeSessions", activeSessions);
        sce.getServletContext().setAttribute("metrics.requestCount", requestCount);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // no-op
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        activeSessions.incrementAndGet();
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        activeSessions.decrementAndGet();
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        requestCount.incrementAndGet();
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        // no-op
    }
}

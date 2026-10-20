package com.demo.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.http.HttpServletRequest;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Request listener: reacts to the creation and destruction of each
 * {@link ServletRequest}. Increments the global request counter.
 */
public class AppRequestListener implements ServletRequestListener {

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        ServletRequest request = sre.getServletRequest();
        String line = describe(request);
        System.out.println("[AppRequestListener] requestInitialized -- " + line);
        incrementRequestCount(request.getServletContext());
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        ServletRequest request = sre.getServletRequest();
        String line = describe(request);
        System.out.println("[AppRequestListener] requestDestroyed -- " + line);
    }

    private String describe(ServletRequest request) {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest http = (HttpServletRequest) request;
            return http.getMethod() + " " + http.getRequestURI();
        }
        return request.toString();
    }

    private void incrementRequestCount(ServletContext ctx) {
        Object attr = ctx.getAttribute(AppContextListener.ATTR_REQUEST_COUNT);
        if (attr instanceof AtomicInteger) {
            ((AtomicInteger) attr).incrementAndGet();
        }
    }
}
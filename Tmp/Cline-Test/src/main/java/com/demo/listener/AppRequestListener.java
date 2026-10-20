package com.demo.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
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
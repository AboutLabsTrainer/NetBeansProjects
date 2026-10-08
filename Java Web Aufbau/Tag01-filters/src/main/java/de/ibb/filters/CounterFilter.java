package de.ibb.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * CounterFilter – zählt Requests thread-sicher und stellt den Zähler
 * als Request-Attribut zur Verfügung.
 */
public class CounterFilter implements Filter {

    private static final AtomicInteger REQUEST_COUNT = new AtomicInteger(0);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("CounterFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        int current = REQUEST_COUNT.incrementAndGet();
        request.setAttribute("requestCount", current);

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("CounterFilter destroyed");
    }
}


package de.ibb.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import de.ibb.logging.RequestLogEntry;
import de.ibb.logging.RequestLogStore;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * LoggingFilter – loggt alle Requests (Timestamp, Method, URL, Duration, Status)
 * und warnt ab 500 ms.
 */
public class LoggingFilter implements Filter {

    private static final DateTimeFormatter TIME_FORMATTER =
            DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    @Override
    public void init(FilterConfig config) throws ServletException {
        System.out.println("LoggingFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String method = httpRequest.getMethod();
        String uri = httpRequest.getRequestURI();
        String queryString = httpRequest.getQueryString();
        String fullUrl = uri + (queryString != null ? "?" + queryString : "");

        long startTime = System.currentTimeMillis();
        LocalDateTime requestTime = LocalDateTime.now();
        String timestamp = requestTime.format(TIME_FORMATTER);

        System.out.println("\n=== REQUEST START ===");
        System.out.println("Time: " + timestamp);
        System.out.println("Method: " + method);
        System.out.println("URL: " + fullUrl);

        try {
            chain.doFilter(request, response);
        } finally {
            long duration = System.currentTimeMillis() - startTime;
            int status = httpResponse.getStatus();

            System.out.println("Status: " + status);
            System.out.println("Duration: " + duration + "ms");

            if (duration > 500) {
                System.err.println("⚠️ SLOW REQUEST: " + fullUrl + " took " + duration + "ms");
            }

            RequestLogStore.add(new RequestLogEntry(requestTime, method, fullUrl, status, duration));
            System.out.println("=== REQUEST END ===\n");
        }
    }

    @Override
    public void destroy() {
        System.out.println("LoggingFilter destroyed");
    }
}


package de.ibb.examples.tag3.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Liest die Zähler, die der Listener im ServletContext ablegt.
 */
@WebServlet(name = "MetricsServlet", urlPatterns = {"/metrics"})
public class MetricsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object start = getServletContext().getAttribute("app.startTime");
        AtomicInteger sessions = (AtomicInteger) getServletContext().getAttribute("metrics.activeSessions");
        AtomicInteger requests = (AtomicInteger) getServletContext().getAttribute("metrics.requestCount");

        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html><html><body>");
            out.println("<h1>App Metrics</h1>");
            out.println("<p>Start: " + start + "</p>");
            out.println("<p>Active Sessions: " + (sessions != null ? sessions.get() : "n/a") + "</p>");
            out.println("<p>Total Requests: " + (requests != null ? requests.get() : "n/a") + "</p>");
            out.println("</body></html>");
        }
    }
}

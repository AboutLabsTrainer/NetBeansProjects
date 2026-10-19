package de.ibb.examples.tag3.web;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;

/**
 * Zeigt den Servlet-Lebenszyklus: init, wiederholte Requests und destroy.
 */
@WebServlet(name = "LifecycleDemoServlet", urlPatterns = {"/lifecycle"})
public class LifecycleDemoServlet extends HttpServlet {

    private Instant initTime;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.initTime = Instant.now();
        getServletContext().log("LifecycleDemoServlet init at " + initTime);
    }

    @Override
    public void destroy() {
        getServletContext().log("LifecycleDemoServlet destroy called at " + Instant.now());
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html><html><body>");
            out.println("<h1>Lifecycle Demo</h1>");
            out.println("<p>init() time: " + initTime + "</p>");
            out.println("<p>Servlet Name: " + getServletName() + "</p>");
            out.println("<p>Thread: " + Thread.currentThread().getName() + "</p>");
            out.println("</body></html>");
        }
    }
}

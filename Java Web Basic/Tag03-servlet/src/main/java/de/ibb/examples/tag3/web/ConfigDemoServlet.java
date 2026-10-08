package de.ibb.examples.tag3.web;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Stellt ServletConfig-Init-Parameter und ServletContext-Parameter gegenüber.
 */
@WebServlet(
    name = "ConfigDemoServlet",
    urlPatterns = {"/config"},
    initParams = {
        @WebInitParam(name = "featureFlag", value = "on"),
        @WebInitParam(name = "teacher", value = "Simon")
    }
)
public class ConfigDemoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletConfig cfg = getServletConfig();
        ServletContext ctx = getServletContext();

        String feature = cfg.getInitParameter("featureFlag");
        String teacher = cfg.getInitParameter("teacher");
        String appName = ctx.getInitParameter("appName");
        String build = ctx.getInitParameter("buildNumber");

        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html><html><body>");
            out.println("<h1>Config Demo</h1>");
            out.println("<h3>ServletConfig (init-params)</h3>");
            out.printf("<p>featureFlag = %s</p>", feature);
            out.printf("<p>teacher = %s</p>", teacher);
            out.println("<h3>ServletContext (context-params)</h3>");
            out.printf("<p>appName = %s</p>", appName);
            out.printf("<p>buildNumber = %s</p>", build);
            out.println("</body></html>");
        }
    }
}

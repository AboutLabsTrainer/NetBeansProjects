package de.ibb.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Startseite nach Encoding-, Logging- und Counter-Filter. Zeigt den Request-Zähler aus der Kette.
 */
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Object countAttr = req.getAttribute("requestCount");
        int count = (countAttr instanceof Integer) ? (Integer) countAttr : -1;

        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"de\">");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\"/>");
            out.println("<title>Filter Demo - Home</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Filter Demo – Home</h1>");
            out.println("<p>Willkommen! Dieses Projekt demonstriert Encoding-, Logging-, Security- und Counter-Filter.</p>");
            if (count >= 0) {
                out.println("<p><strong>Request-Count:</strong> " + count + "</p>");
            } else {
                out.println("<p><em>Request-Count derzeit nicht verfügbar.</em></p>");
            }
            out.println("<ul>");
            out.println("<li><a href=\"" + req.getContextPath() + "/\">Home</a></li>");
            out.println("<li><a href=\"" + req.getContextPath() + "/admin\">Admin-Bereich</a></li>");
            out.println("<li><a href=\"" + req.getContextPath() + "/login\">Login</a></li>");
            out.println("</ul>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}


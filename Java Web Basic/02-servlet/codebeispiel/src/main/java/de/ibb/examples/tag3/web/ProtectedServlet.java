package de.ibb.examples.tag3.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Prüft die Session, bevor der geschützte Inhalt ausgegeben wird.
 */
@WebServlet(name = "ProtectedServlet", urlPatterns = {"/protected"})
public class ProtectedServlet extends HttpServlet {

    private static final String SESSION_USER = "user";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute(SESSION_USER) == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"de\">");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<title>Geschützter Bereich</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Willkommen im geschützten Bereich!</h1>");
            out.println("<p>Nur eingeloggte Benutzer können diese Seite sehen.</p>");
            out.println("<p><a href=\"" + req.getContextPath() + "/dashboard\">Zurück zum Dashboard</a></p>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}


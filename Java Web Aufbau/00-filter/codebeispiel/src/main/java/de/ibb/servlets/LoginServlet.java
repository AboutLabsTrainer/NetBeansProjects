package de.ibb.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Sehr einfacher Login-Servlet nur für die Demo:
 * - GET: zeigt Formular
 * - POST: setzt ein "user"-Attribut in der Session und leitet nach /admin um
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"de\">");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\"/>");
            out.println("<title>Filter Demo - Login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Login</h1>");
            out.println("<form method=\"post\" action=\"" + req.getContextPath() + "/login\">");
            out.println("  <label>Username: <input type=\"text\" name=\"username\"/></label><br/>");
            out.println("  <button type=\"submit\">Login</button>");
            out.println("</form>");
            out.println("<p><a href=\"" + req.getContextPath() + "/\">Zurück zur Homepage</a></p>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        if (username == null || username.isBlank()) {
            username = "demoUser";
        }

        HttpSession session = req.getSession(true);
        session.setAttribute("user", username);

        resp.sendRedirect(req.getContextPath() + "/admin");
    }
}


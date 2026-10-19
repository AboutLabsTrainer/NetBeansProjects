package de.ibb.examples.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Geschützte Seite: ohne Session-Attribut user geht es zurück zum Login.
 */
@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String user = (String) session.getAttribute("user");
        Long loginTime = (Long) session.getAttribute("loginTime");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>Dashboard</title></head><body>");
        out.println("<h1>Welcome to your Dashboard!</h1>");
        out.println("<p>Logged in as: <strong>" + user + "</strong></p>");
        out.println("<p>Login time: " + new Date(loginTime) + "</p>");
        out.println("<p>Session ID: " + session.getId() + "</p>");
        out.println("<form method='post' action='" + request.getContextPath() + "/logout'>");
        out.println("  <button type='submit'>Logout</button>");
        out.println("</form>");
        out.println("</body></html>");
    }
}
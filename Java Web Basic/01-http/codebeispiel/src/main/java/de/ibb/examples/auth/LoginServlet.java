package de.ibb.examples.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * GET zeigt das Formular, POST legt bei gültigen Daten user in der Session ab.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String error = request.getParameter("error");
        String message = request.getParameter("message");
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><title>Login</title></head>");
        out.println("<body>");
        out.println("<h1>Login</h1>");
        if ("true".equals(error)) {
            out.println("<p style='color:red'>Invalid credentials!</p>");
        }
        if (message != null) {
            out.println("<p style='color:green'>"+ message +"</p>");
        }
        out.println("<form method='post' action='" + request.getContextPath() + "/login'>");
        out.println("  <input type='email' name='email' placeholder='Email' required><br>");
        out.println("  <input type='password' name='password' placeholder='Password' required><br>");
        out.println("  <button type='submit'>Login</button>");
        out.println("</form>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if ("anna@example.com".equals(email) && "password123".equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", email);
            session.setAttribute("loginTime", System.currentTimeMillis());
            response.sendRedirect(request.getContextPath() + "/dashboard");
        } else {
            response.sendRedirect(request.getContextPath() + "/login?error=true");
        }
    }
}
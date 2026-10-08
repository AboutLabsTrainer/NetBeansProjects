package de.ibb.examples.tag3.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Ziel eines RequestDispatcher-Forwards aus dem Dispatcher-Beispiel.
 */
@WebServlet(name = "ForwardTargetServlet", urlPatterns = {"/forwardTarget"})
public class ForwardTargetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<div style='border:1px solid #ccc;padding:10px;'>");
            out.println("<strong>Forward/Include Target</strong>");
            out.println("<p>Request URI: " + req.getRequestURI() + "</p>");
            out.println("</div>");
        }
    }
}

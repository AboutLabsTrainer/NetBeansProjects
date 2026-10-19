package de.ibb.examples.tag3.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Übt RequestDispatcher: forward und include.
 */
@WebServlet(name = "DispatcherDemoServlet", urlPatterns = {"/dispatch"})
public class DispatcherDemoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mode = req.getParameter("mode");
        if ("include".equalsIgnoreCase(mode)) {
            resp.getWriter().println("<p>Before include</p>");
            RequestDispatcher rd = req.getRequestDispatcher("/forwardTarget");
            rd.include(req, resp);
            resp.getWriter().println("<p>After include</p>");
        } else {
            RequestDispatcher rd = req.getRequestDispatcher("/forwardTarget");
            rd.forward(req, resp);
        }
    }
}

package de.ibb.examples.tag3.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Löst mit sendError die in web.xml gemappten Fehlerseiten aus.
 */
@WebServlet(name = "ErrorDemoServlet", urlPatterns = {"/error-demo"})
public class ErrorDemoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        if ("404".equals(code)) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Demo 404");
        } else if ("500".equals(code)) {
            throw new ServletException("Demo 500");
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown code. Use 404 or 500.");
        }
    }
}

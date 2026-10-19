package de.ibb.examples;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Liest den Request-Parameter name und begrüßt damit, sonst mit World.
 */
@WebServlet(name = "GreetServlet", urlPatterns = {"/greet"})
public class GreetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain;charset=UTF-8");

        String name = request.getParameter("name");
        if (name == null || name.isBlank()) {
            name = "World";
        }

        response.getWriter().printf("Hello, %s!", name);
    }
}

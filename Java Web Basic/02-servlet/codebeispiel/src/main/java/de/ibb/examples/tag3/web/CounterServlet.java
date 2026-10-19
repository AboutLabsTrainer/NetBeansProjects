package de.ibb.examples.tag3.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Erhöht einen Zähler ohne Synchronisation, um Race Conditions sichtbar zu machen.
 */
@WebServlet(name = "CounterServlet", urlPatterns = {"/counter"})
public class CounterServlet extends HttpServlet {
    private int counter = 0; // deliberately NOT thread-safe

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        counter++; // Race condition under load
        resp.setContentType("text/plain;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("UNSAFE counter = " + counter);
        }
    }
}

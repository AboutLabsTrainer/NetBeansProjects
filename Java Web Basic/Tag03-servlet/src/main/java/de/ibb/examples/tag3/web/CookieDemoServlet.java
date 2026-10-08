package de.ibb.examples.tag3.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;

/**
 * Setzt ein Cookie und liest vorhandene Cookies aus dem Request.
 */
@WebServlet(name = "CookieDemoServlet", urlPatterns = {"/cookie"})
public class CookieDemoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // set cookie if absent
        boolean present = false;
        if (req.getCookies() != null) {
            for (Cookie c : req.getCookies()) {
                if ("demo".equals(c.getName())) {
                    present = true;
                }
            }
        }
        if (!present) {
            Cookie c = new Cookie("demo", "hello-servlet");
            c.setPath(req.getContextPath());
            c.setMaxAge((int) Duration.ofHours(1).getSeconds());
            resp.addCookie(c);
        }

        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html><html><body>");
            out.println("<h1>Cookie Demo</h1>");
            out.println("<p>Cookies vom Client:</p><ul>");
            if (req.getCookies() != null) {
                for (Cookie c : req.getCookies()) {
                    out.println("<li>" + c.getName() + " = " + c.getValue() + "</li>");
                }
            }
            out.println("</ul>");
            out.println("</body></html>");
        }
    }
}

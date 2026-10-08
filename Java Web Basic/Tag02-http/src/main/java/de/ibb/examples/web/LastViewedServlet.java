package de.ibb.examples.web;

import de.ibb.examples.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Liest das Session-Attribut lastViewed und zeigt das zuletzt betrachtete Produkt.
 */
@WebServlet("/lastViewed")
public class LastViewedServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false); // don't create
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>Last Viewed</title></head><body>");
        out.println("<h1>Zuletzt angesehen</h1>");

        if (session == null) {
            out.println("<p>❌ Keine Session gefunden!</p>");
        } else {
            Product lastViewed = (Product) session.getAttribute("lastViewedProduct");
            if (lastViewed == null) {
                out.println("<p>Du hast noch kein Produkt angesehen.</p>");
            } else {
                out.println("<p>✅ Zuletzt angesehen: <strong>" + lastViewed.getName() + "</strong></p>");
                out.println("<p>Preis: €" + String.format("%.2f", lastViewed.getPrice()) + "</p>");
            }
        }
        out.println("<hr>");
        out.println("<p><a href='" + request.getContextPath() + "/products'>Zur Produktliste</a></p>");
        out.println("</body></html>");
    }
}
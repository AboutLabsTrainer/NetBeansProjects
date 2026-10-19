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
import java.util.ArrayList;
import java.util.List;

/**
 * Liest die Produkt-Id aus dem Pfad und legt das Produkt als lastViewed in der Session ab.
 */
@WebServlet("/product/*")
public class ProductDetailServlet extends HttpServlet {

    private static final List<Product> PRODUCTS = new ArrayList<>();
    static {
        PRODUCTS.add(new Product(1L, "Laptop Dell XPS 15", "Electronics", 1299.99));
        PRODUCTS.add(new Product(2L, "iPhone 15 Pro", "Electronics", 1199.00));
        PRODUCTS.add(new Product(3L, "Samsung Galaxy S24", "Electronics", 899.00));
        PRODUCTS.add(new Product(4L, "MacBook Air M2", "Electronics", 1449.00));
        PRODUCTS.add(new Product(5L, "Logitech MX Master 3S", "Electronics", 99.99));
        PRODUCTS.add(new Product(6L, "Clean Code (Robert Martin)", "Books", 42.50));
        PRODUCTS.add(new Product(7L, "Effective Java (Joshua Bloch)", "Books", 47.99));
        PRODUCTS.add(new Product(8L, "Design Patterns (GoF)", "Books", 54.99));
        PRODUCTS.add(new Product(9L, "The Pragmatic Programmer", "Books", 39.99));
        PRODUCTS.add(new Product(10L, "Head First Java", "Books", 44.99));
        PRODUCTS.add(new Product(11L, "Nike Air Max 270", "Clothing", 149.99));
        PRODUCTS.add(new Product(12L, "Adidas Ultraboost 23", "Clothing", 179.99));
        PRODUCTS.add(new Product(13L, "Levi's 501 Jeans", "Clothing", 89.99));
        PRODUCTS.add(new Product(14L, "North Face Jacket", "Clothing", 199.99));
        PRODUCTS.add(new Product(15L, "Tommy Hilfiger Polo", "Clothing", 79.99));
        PRODUCTS.add(new Product(16L, "Yoga Mat Premium", "Sports", 34.99));
        PRODUCTS.add(new Product(17L, "Dumbbell Set 20kg", "Sports", 89.99));
        PRODUCTS.add(new Product(18L, "Fitbit Charge 6", "Sports", 159.99));
        PRODUCTS.add(new Product(19L, "Protein Powder 1kg", "Sports", 29.99));
        PRODUCTS.add(new Product(20L, "Running Shoes Asics", "Sports", 129.99));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pathInfo = request.getPathInfo();
        if (pathInfo == null || "/".equals(pathInfo)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Product ID missing");
            return;
        }

        String idString = pathInfo.substring(1);
        try {
            Long id = Long.parseLong(idString);
            Product product = findProductById(id);
            if (product == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Product with ID " + id + " not found");
                return;
            }

            HttpSession session = request.getSession(); // create if not exists
            session.setAttribute("lastViewedProduct", product);
            session.setAttribute("lastViewedTime", System.currentTimeMillis());

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html><head><title>" + product.getName() + "</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; margin: 40px; }");
            out.println(".product-card { border: 1px solid #ddd; padding: 20px; max-width: 600px; }");
            out.println(".price { color: #4CAF50; font-size: 24px; font-weight: bold; }");
            out.println(".category { color: #666; font-style: italic; }");
            out.println(".session-info { background: #f0f0f0; padding: 10px; margin-top: 20px; }");
            out.println("a { color: #4CAF50; text-decoration: none; }");
            out.println("</style></head><body>");
            out.println("<div class='product-card'>");
            out.println("<h1>" + product.getName() + "</h1>");
            out.println("<p class='category'>Category: " + product.getCategory() + "</p>");
            out.println("<p class='price'>€" + String.format("%.2f", product.getPrice()) + "</p>");
            out.println("<p>Product ID: " + product.getId() + "</p>");
            out.println("<div class='session-info'>");
            out.println("<h3>Session-Scope Demo</h3>");
            out.println("<p>Session ID: " + session.getId() + "</p>");
            out.println("<p>Dieses Produkt liegt server-seitig in deiner Session.</p>");
            out.println("</div>");
            out.println("<hr><p><a href='" + request.getContextPath() + "/products'>← Back to Product List</a></p>");
            out.println("</div></body></html>");
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid product ID: " + idString);
        }
    }

    private Product findProductById(Long id) {
        for (Product p : PRODUCTS) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }
}
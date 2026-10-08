package de.ibb.examples.web;

import de.ibb.examples.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Liefert eine feste Produktliste und übt die Antwort eines GET-Servlets.
 */
@WebServlet("/products")
public class ProductListServlet extends HttpServlet {

    private static final List<Product> PRODUCTS = new ArrayList<>();
    static {
        // Electronics
        PRODUCTS.add(new Product(1L, "Laptop Dell XPS 15", "Electronics", 1299.99));
        PRODUCTS.add(new Product(2L, "iPhone 15 Pro", "Electronics", 1199.00));
        PRODUCTS.add(new Product(3L, "Samsung Galaxy S24", "Electronics", 899.00));
        PRODUCTS.add(new Product(4L, "MacBook Air M2", "Electronics", 1449.00));
        PRODUCTS.add(new Product(5L, "Logitech MX Master 3S", "Electronics", 99.99));
        // Books
        PRODUCTS.add(new Product(6L, "Clean Code (Robert Martin)", "Books", 42.50));
        PRODUCTS.add(new Product(7L, "Effective Java (Joshua Bloch)", "Books", 47.99));
        PRODUCTS.add(new Product(8L, "Design Patterns (GoF)", "Books", 54.99));
        PRODUCTS.add(new Product(9L, "The Pragmatic Programmer", "Books", 39.99));
        PRODUCTS.add(new Product(10L, "Head First Java", "Books", 44.99));
        // Clothing
        PRODUCTS.add(new Product(11L, "Nike Air Max 270", "Clothing", 149.99));
        PRODUCTS.add(new Product(12L, "Adidas Ultraboost 23", "Clothing", 179.99));
        PRODUCTS.add(new Product(13L, "Levi's 501 Jeans", "Clothing", 89.99));
        PRODUCTS.add(new Product(14L, "North Face Jacket", "Clothing", 199.99));
        PRODUCTS.add(new Product(15L, "Tommy Hilfiger Polo", "Clothing", 79.99));
        // Sports
        PRODUCTS.add(new Product(16L, "Yoga Mat Premium", "Sports", 34.99));
        PRODUCTS.add(new Product(17L, "Dumbbell Set 20kg", "Sports", 89.99));
        PRODUCTS.add(new Product(18L, "Fitbit Charge 6", "Sports", 159.99));
        PRODUCTS.add(new Product(19L, "Protein Powder 1kg", "Sports", 29.99));
        PRODUCTS.add(new Product(20L, "Running Shoes Asics", "Sports", 129.99));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Product List</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 40px; }");
        out.println("table { border-collapse: collapse; width: 100%; }");
        out.println("th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }");
        out.println("th { background-color: #4CAF50; color: white; }");
        out.println("tr:hover { background-color: #f5f5f5; }");
        out.println("a { color: #4CAF50; text-decoration: none; }");
        out.println("a:hover { text-decoration: underline; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Product Catalog</h1>");
        out.println("<p>Total products: " + PRODUCTS.size() + "</p>");
        out.println("<table>");
        out.println("<tr><th>ID</th><th>Name</th><th>Category</th><th>Price</th><th>Action</th></tr>");
        for (Product product : PRODUCTS) {
            out.println("<tr>");
            out.println("<td>" + product.getId() + "</td>");
            out.println("<td>" + product.getName() + "</td>");
            out.println("<td>" + product.getCategory() + "</td>");
            out.println("<td>€" + String.format("%.2f", product.getPrice()) + "</td>");
            out.println("<td><a href='" + request.getContextPath() + "/product/" + product.getId() + "'>View Details</a></td>");
            out.println("</tr>");
        }
        out.println("</table>");
        out.println("</body></html>");
    }
}
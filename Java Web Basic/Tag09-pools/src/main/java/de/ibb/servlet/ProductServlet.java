package de.ibb.servlet;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Liest Produkte über die per @Resource injizierte DataSource jdbc/jwebb_tag09_pools.
 */
@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    @Resource(name = "jdbc/jwebb_tag09_pools")
    private DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM jwebb_products")) {

            java.util.List<String> productNames = new java.util.ArrayList<>();

            while (rs.next()) {
                productNames.add(rs.getString("name"));
            }

            request.setAttribute("lookupMode", "Resource Injection");
            request.setAttribute("productNames", productNames);
            request.getRequestDispatcher("/WEB-INF/views/products.jsp")
                    .forward(request, response);

        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }
}


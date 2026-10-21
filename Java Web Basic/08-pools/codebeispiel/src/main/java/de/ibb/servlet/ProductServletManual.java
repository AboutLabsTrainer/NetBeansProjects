package de.ibb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Holt die DataSource jdbc/ibb einmal per JNDI-Lookup in init().
 */
@WebServlet("/products-manual")
public class ProductServletManual extends HttpServlet {

    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        try {
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/ibb");
        } catch (NamingException e) {
            throw new ServletException("JNDI lookup failed", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM jwebb08_products")) {

            java.util.List<String> productNames = new java.util.ArrayList<>();

            while (rs.next()) {
                productNames.add(rs.getString("name"));
            }

            request.setAttribute("lookupMode", "Manual JNDI Lookup");
            request.setAttribute("productNames", productNames);
            request.getRequestDispatcher("/WEB-INF/views/products.jsp")
                    .forward(request, response);

        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }
}


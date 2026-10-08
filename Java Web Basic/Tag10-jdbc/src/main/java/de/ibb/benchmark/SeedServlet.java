package de.ibb.benchmark;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.sql.DataSource;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * Legt jwebb_products an und füllt sie über den Pool jdbc/jwebb_tag10_jdbc.
 */
@WebServlet(name = "SeedServlet", urlPatterns = {"/initdb"})
public class SeedServlet extends HttpServlet {

    @Resource(lookup = "jdbc/jwebb_tag10_jdbc")
    private DataSource ds;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain;charset=UTF-8");
        try (PrintWriter out = resp.getWriter();
             Connection conn = ds.getConnection()) {

            try (Statement st = conn.createStatement()) {
                st.execute("CREATE TABLE IF NOT EXISTS jwebb_products (\n" +
                        "  id INT PRIMARY KEY AUTO_INCREMENT,\n" +
                        "  name VARCHAR(200) NOT NULL,\n" +
                        "  price DECIMAL(10,2) NOT NULL,\n" +
                        "  stock INT NOT NULL\n" +
                        ")");
                st.execute("DELETE FROM jwebb_products"); // reset
            }

            try (PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO jwebb_products(name, price, stock) VALUES (?,?,?)")) {
                conn.setAutoCommit(false);
                for (int i = 1; i <= 1000; i++) {
                    ps.setString(1, "Product " + i);
                    ps.setDouble(2, 10.0 + (i % 100));
                    ps.setInt(3, (i % 50) + 1);
                    ps.addBatch();
                }
                ps.executeBatch();
                conn.commit();
                conn.setAutoCommit(true);
            }

            resp.setStatus(200);
            out.println("OK: Schema jwebb_products created and 1000 products inserted.");
        } catch (Exception e) {
            throw new ServletException("Init failed: " + e.getMessage(), e);
        }
    }
}

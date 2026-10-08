package de.ibb.web;

import de.ibb.jdbc.JdbcProductDao;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

/**
 * Zeigt Produktnamen per JDBC. Gegenstück zu JpaProductServlet auf derselben Datenbank.
 */
@WebServlet(name = "JdbcDemoServlet", urlPatterns = {"/jdbc"})
public class JdbcDemoServlet extends HttpServlet {

    @Inject
    private JdbcProductDao dao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html><html><head><meta charset='UTF-8'><title>JDBC – Products</title>");
            out.println("<link rel='stylesheet' href='assets/styles.css'></head><body>");
            out.println("<h1>JDBC – Product Names via DataSource</h1>");
            out.println("<p><a href='" + req.getContextPath() + "/'>Home</a></p>");
            try {
                List<String> names = dao.findAllProductNames();
                out.println("<ul>");
                for (String n : names) out.println("<li>"+escape(n)+"</li>");
                out.println("</ul>");
            } catch (SQLException e) {
                out.println("<pre class='error'>"+escape(e.getMessage())+"</pre>");
            }
            out.println("</body></html>");
        }
    }

    private String escape(String s) {
        if (s == null) return "";
        return s.replace("&","&amp;").replace("<","&lt;").replace(">","&gt;").replace("\"","&quot;").replace("'","&#39;");
    }
}

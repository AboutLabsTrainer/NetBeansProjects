package de.ibb.ctrl;

import de.ibb.model.Customer;
import de.ibb.util.GConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Programmatisches Login gegen jwebak02_customer. Die JDBC-Daten kommen aus den Context-Parametern in web.xml, nicht aus einem Server-Realm.
 */
@WebServlet(name = "SigninServlet", urlPatterns = {"/signin"})
public class SigninServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/signin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || password == null || email.isBlank() || password.isBlank()) {
            request.setAttribute("error", "Please enter email and password.");
            request.getRequestDispatcher("/signin.jsp").forward(request, response);
            return;
        }

        String hashed = hash(password);
        try (Connection con = new GConnection(getServletContext()).getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "SELECT id, email FROM jwebak02_customer WHERE email=? AND password=?")) {

            ps.setString(1, email);
            ps.setString(2, hashed);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Customer c = new Customer(rs.getLong("id"), rs.getString("email"));
                    HttpSession session = request.getSession(true);
                    // signin.jsp hat schon eine Session angelegt. Neue ID nach dem Login, sonst gilt eine untergeschobene ID weiter (Session Fixation).
                    request.changeSessionId();
                    session.setAttribute("customer", c);
                    response.sendRedirect(request.getContextPath() + "/index.jsp");
                } else {
                    request.setAttribute("error", "Login failed. Check email or password.");
                    request.getRequestDispatcher("/signin.jsp").forward(request, response);
                }
            }
        } catch (SQLException ex) {
            throw new ServletException("Database error: " + ex.getMessage(), ex);
        }
    }

    private String hash(String raw) throws ServletException {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(raw.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new ServletException("SHA-256 not available", e);
        }
    }
}

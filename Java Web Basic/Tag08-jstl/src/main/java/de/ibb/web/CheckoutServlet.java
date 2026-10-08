package de.ibb.web;

import de.ibb.model.Cart;
import de.ibb.model.CartItem;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Legt Warenkorbwerte als Request-Attribute ab und forwarded auf die Checkout-JSP.
 */
@WebServlet(name = "CheckoutServlet", urlPatterns = {"/checkout"})
public class CheckoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Cart cart = new Cart();
        cart.add(new CartItem("Java Web Basic Course", new BigDecimal("99.99"), 1));
        cart.add(new CartItem("Spring Boot Masterclass", new BigDecimal("149.99"), 2));
        cart.add(new CartItem("Legacy Course", new BigDecimal("29.99"), 0));

        request.setAttribute("cartItems", cart.getItems());
        request.setAttribute("subtotal", cart.getSubtotal());
        request.setAttribute("tax", cart.getTax());
        request.setAttribute("total", cart.getTotal());
        request.setAttribute("orderDate", new Date());

        request.getRequestDispatcher("/WEB-INF/views/checkout.jsp").forward(request, response);
    }
}

package de.ibb.servlet;

import de.ibb.dao.ProductDAO;
import de.ibb.model.Product;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * Ruft das DAO per @EJB auf und reicht die Liste an die JSP weiter.
 */
@WebServlet("/products-dao")
public class ProductDaoServlet extends HttpServlet {

    @EJB
    private ProductDAO productDAO;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String search = request.getParameter("search");

        List<Product> products;
        if (search != null && !search.trim().isEmpty()) {
            products = productDAO.searchByName(search.trim());
        } else {
            products = productDAO.findAll();
        }

        request.setAttribute("products", products);
        request.getRequestDispatcher("/WEB-INF/views/products-dao.jsp")
                .forward(request, response);
    }
}


package de.ibb.web;

import de.ibb.domain.Product;
import de.ibb.service.ProductService;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Legt Produkte über den JPA-Dienst an und listet sie. Gegenstück zum JDBC-Servlet.
 */
@WebServlet(name = "JpaProductServlet", urlPatterns = {"/jpa"})
public class JpaProductServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(JpaProductServlet.class.getName());

    @Inject
    private ProductService service;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        try {
            if ("create".equals(action)) {
                String name = req.getParameter("name");
                BigDecimal price = new BigDecimal(req.getParameter("price"));
                service.createProduct(name, price);
            } else if ("updateStock".equals(action)) {
                Long id = Long.valueOf(req.getParameter("id"));
                int stock = Integer.parseInt(req.getParameter("stock"));
                service.updateStock(id, stock);
            } else if ("delete".equals(action)) {
                Long id = Long.valueOf(req.getParameter("id"));
                service.deleteProduct(id);
            }
        } catch (PersistenceException | IllegalArgumentException e) {
            LOG.log(Level.SEVERE, "JPA-Aufruf fehlgeschlagen", e);
            req.getSession().setAttribute("error", e.getMessage());
        }
        resp.sendRedirect(req.getContextPath() + "/jpa");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();

        List<Product> products = service.findAllProducts();
        String error = (String) req.getSession().getAttribute("error");
        req.getSession().removeAttribute("error");

        out.println("<!DOCTYPE html><html><head><meta charset='UTF-8'><title>JPA – Products</title>");
        out.println("<link rel='stylesheet' href='assets/styles.css'></head><body>");
        out.println("<h1>JPA – Product Management (EclipseLink)</h1>");
        out.println("<p><a href='" + req.getContextPath() + "/'>Home</a></p>");
        if (error != null) {
            out.println("<p class='error'>"+escape(error)+"</p>");
        }
        out.println("<div class='grid'>");

        out.println("<div class='card'><h2>Create Product</h2>");
        out.println("<form method='post'><input type='hidden' name='action' value='create'/>");
        out.println("<label>Name <input name='name' required maxlength='100'></label><br/>");
        out.println("<label>Price <input name='price' required placeholder='12.34'></label><br/>");
        out.println("<button>Create</button></form></div>");

        out.println("<div class='card'><h2>All Products</h2>");
        out.println("<table><tr><th>ID</th><th>Name</th><th>Price</th><th>Stock</th><th>Actions</th></tr>");
        for (Product p : products) {
            out.println("<tr>");
            out.println("<td>"+p.getId()+"</td>");
            out.println("<td>"+escape(p.getName())+"</td>");
            out.println("<td>"+p.getPrice()+"</td>");
            out.println("<td>"+p.getStock()+"</td>");
            out.println("<td>");
            out.println("<form method='post' style='display:inline'><input type='hidden' name='action' value='updateStock'/>");
            out.println("<input type='hidden' name='id' value='"+p.getId()+"'/>");
            out.println("<input name='stock' value='"+p.getStock()+"' size='4'/>");
            out.println("<button>Update</button></form>");
            out.println(" ");
            out.println("<form method='post' style='display:inline' onsubmit='return confirm(\"Delete #"+p.getId()+"?\")'>");
            out.println("<input type='hidden' name='action' value='delete'/>");
            out.println("<input type='hidden' name='id' value='"+p.getId()+"'/>");
            out.println("<button>Delete</button></form>");
            out.println("</td>");
            out.println("</tr>");
        }
        out.println("</table></div>");
        out.println("</div></body></html>");
    }

    private String escape(String s) {
        if (s == null) return "";
        return s.replace("&","&amp;").replace("<","&lt;").replace(">","&gt;").replace("\"","&quot;").replace("'","&#39;");
    }
}

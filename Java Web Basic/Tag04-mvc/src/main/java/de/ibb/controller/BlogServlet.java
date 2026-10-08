package de.ibb.controller;

import de.ibb.dao.BlogPostDAO;
import de.ibb.model.BlogPost;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Controller aus web.xml: liest den Request und reicht per Forward an die JSP weiter.
 */

public class BlogServlet extends HttpServlet {

    private BlogPostDAO dao;
    private String appTitle;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.dao = new BlogPostDAO();
        ServletContext ctx = config.getServletContext();
        this.appTitle = ctx.getInitParameter("app.title");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        req.setAttribute("appTitle", appTitle);

        if (idParam == null || idParam.isBlank()) {
            List<BlogPost> posts = dao.findAll();
            req.setAttribute("posts", posts);
            req.getRequestDispatcher("/WEB-INF/views/blog-list.jsp").forward(req, resp);
        } else {
            try {
                long id = Long.parseLong(idParam);
                Optional<BlogPost> maybe = dao.findById(id);
                if (maybe.isPresent()) {
                    req.setAttribute("post", maybe.get());
                    req.getRequestDispatcher("/WEB-INF/views/blog-post.jsp").forward(req, resp);
                } else {
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND, "BlogPost not found");
                }
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid id");
            }
        }
    }
}

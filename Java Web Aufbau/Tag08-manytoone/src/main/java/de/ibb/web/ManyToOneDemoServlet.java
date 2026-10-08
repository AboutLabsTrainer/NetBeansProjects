package de.ibb.web;

import de.ibb.domain.Author;
import de.ibb.domain.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Legt Autor und Buch an und zeigt, dass das Buch seinen Autor über die JPA-Beziehung erreicht.
 */
@WebServlet(name = "ManyToOneDemoServlet", urlPatterns = {"/jpa"})
public class ManyToOneDemoServlet extends HttpServlet {

    @PersistenceContext(unitName = "Tag8PU")
    private EntityManager em;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if ("addAuthor".equals(action)) {
            String name = req.getParameter("name");
            em.persist(new Author(name));
        } else if ("addBook".equals(action)) {
            String title = req.getParameter("title");
            Long authorId = Long.valueOf(req.getParameter("authorId"));
            Author a = em.find(Author.class, authorId);
            em.persist(new Book(title, a));
        } else if ("deleteBook".equals(action)) {
            Long id = Long.valueOf(req.getParameter("bookId"));
            Book b = em.find(Book.class, id);
            if (b != null) em.remove(b);
        }
        resp.sendRedirect(req.getContextPath() + "/jpa");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();

        List<Author> authors = em.createQuery("select a from Author a order by a.id", Author.class).getResultList();
        List<Book> books = em.createQuery("select b from Book b order by b.id", Book.class).getResultList();

        out.println("<!DOCTYPE html><html><head><meta charset='UTF-8'><title>Tag8 – ManyToOne</title>");
        out.println("<link rel='stylesheet' href='assets/styles.css'></head><body>");
        out.println("<h1>Tag8 – @ManyToOne (Author &amp; Book)</h1>");
        out.println("<p><a href='" + req.getContextPath() + "/'>Zurück</a></p>");

        out.println("<div class='grid'>");
        out.println("<div class='card'><h2>Authors</h2>");
        out.println("<form method='post'><input type='hidden' name='action' value='addAuthor'/>");
        out.println("<input name='name' placeholder='Name' required maxlength='100'/>");
        out.println("<button>+ Author</button></form><hr/>");
        out.println("<ul>");
        for (Author a : authors) {
            out.println("<li>#"+a.getId()+": "+escape(a.getName())+" ("+a.getBooks().size()+" books)</li>");
        }
        out.println("</ul></div>");

        out.println("<div class='card'><h2>Books</h2>");
        out.println("<form method='post'><input type='hidden' name='action' value='addBook'/>");
        out.println("<input name='title' placeholder='Title' required maxlength='160'/>");
        out.println("<select name='authorId'>");
        for (Author a : authors) {
            out.println("<option value='"+a.getId()+"'>"+escape(a.getName())+"</option>");
        }
        out.println("</select><button>+ Book</button></form><hr/>");
        out.println("<ol>");
        for (Book b : books) {
            out.println("<li>#"+b.getId()+": "+escape(b.getTitle())+" – by Author #"+b.getAuthor().getId()+" ");
            out.println("<form method='post' style='display:inline' onsubmit='return confirm(\"Delete book #" + b.getId() + "?\")'>");
            out.println("<input type='hidden' name='action' value='deleteBook'/>");
            out.println("<input type='hidden' name='bookId' value='"+b.getId()+"'/>");
            out.println("<button>Delete</button></form>");
            out.println("</li>");
        }
        out.println("</ol></div>");

        out.println("</div></body></html>");
    }

    private String escape(String s) {
        if (s == null) return "";
        return s.replace("&","&amp;").replace("<","&lt;").replace(">","&gt;")
                .replace("\"","&quot;").replace("'","&#39;");
    }
}

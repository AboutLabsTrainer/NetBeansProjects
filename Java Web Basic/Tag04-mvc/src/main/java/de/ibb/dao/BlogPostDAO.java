package de.ibb.dao;

import de.ibb.model.BlogPost;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Hält die Blog-Einträge im Speicher und sucht sie für den Controller.
 */

public class BlogPostDAO {

    private static final List<BlogPost> POSTS = new ArrayList<>();

    static {
        POSTS.add(new BlogPost(1L, "Willkommen im Mini-Blog",
                "Dies ist der erste Beitrag. MVC mit Servlets & JSP (EL) macht Spaß!",
                "Elyndra", LocalDate.now().minusDays(2)));
        POSTS.add(new BlogPost(2L, "Deployment Descriptor verstehen",
                "Heute üben wir web.xml: Servlet-Mappings, Fehlerseiten, Session-Config, Welcome-Files.",
                "Simon", LocalDate.now().minusDays(1)));
        POSTS.add(new BlogPost(3L, "Forward statt Redirect",
                "Für die View-Verknüpfung nutzt der Controller RequestDispatcher#forward(...).",
                "Nova", LocalDate.now()));
    }

    public List<BlogPost> findAll() {
        return Collections.unmodifiableList(POSTS);
    }

    public Optional<BlogPost> findById(long id) {
        return POSTS.stream().filter(p -> p.getId() == id).findFirst();
    }
}

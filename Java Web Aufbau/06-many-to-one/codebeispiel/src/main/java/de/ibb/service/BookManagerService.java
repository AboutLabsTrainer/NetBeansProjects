package de.ibb.service;

import de.ibb.domain.Author;
import de.ibb.domain.Book;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

/**
 * Service-Klasse zur Verwaltung von Autoren und Büchern.
 * Nutzt Jakarta EJB (@Stateless) für automatisches Transaktionsmanagement.
 */
@Stateless
public class BookManagerService {

    @PersistenceContext(unitName = "Tag8PU")
    private EntityManager em;

    public void addAuthor(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Author name cannot be empty");
        }
        em.persist(new Author(name.trim()));
    }

    public void addBook(String title, Long authorId) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Book title cannot be empty");
        }
        
        Author author = em.find(Author.class, authorId);
        if (author == null) {
            throw new IllegalArgumentException("Author with ID " + authorId + " not found");
        }
        
        Book book = new Book(title.trim(), author);
        em.persist(book);
        
        // Best Practice für bidirektionale Beziehungen: Beide Seiten im Speicher synchron halten
        author.getBooks().add(book);
    }

    public void deleteBook(Long bookId) {
        Book book = em.find(Book.class, bookId);
        if (book != null) {
            // Aus der Liste des Autors entfernen, um den Zustand im Speicher synchron zu halten
            if (book.getAuthor() != null) {
                book.getAuthor().getBooks().remove(book);
            }
            em.remove(book);
        }
    }

    public List<Author> findAllAuthorsFetchBooks() {
        // "join fetch" verhindert das N+1-Select-Problem beim Laden der Bücher-Anzahl in der UI
        return em.createQuery(
            "select distinct a from Author a left join fetch a.books order by a.id", 
            Author.class
        ).getResultList();
    }

    public List<Book> findAllBooks() {
        // Da die UI das Buch-Objekt anzeigt und dessen Autor benötigt, 
        // optimiert ein "join fetch" auch hier die DB-Abfragen.
        return em.createQuery(
            "select b from Book b join fetch b.author order by b.id", 
            Book.class
        ).getResultList();
    }
}

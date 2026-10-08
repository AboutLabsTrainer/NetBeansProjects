package com.example.bookshop.book;

import com.example.bookshop.author.AuthorEntity;
import com.example.bookshop.author.AuthorNotFoundException;
import com.example.bookshop.author.AuthorRepository;
import com.example.bookshop.monitoring.BookMetrics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
public class BookService {

    private static final Logger log = LoggerFactory.getLogger(BookService.class);

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookMetrics bookMetrics;

    public BookService(
            BookRepository bookRepository,
            AuthorRepository authorRepository,
            BookMetrics bookMetrics
    ) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookMetrics = bookMetrics;
    }

    public List<BookResponse> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(BookResponse::from)
                .toList();
    }

    public BookResponse findById(Long id) {
        return BookResponse.from(findBookEntity(id));
    }

    public List<BookResponse> findByAuthorId(Long authorId) {
        Long safeAuthorId = Objects.requireNonNull(authorId, "authorId");

        if (!authorRepository.existsById(safeAuthorId)) {
            log.warn("Autor wurde nicht gefunden: authorId={}", safeAuthorId);
            throw new AuthorNotFoundException(safeAuthorId);
        }

        return bookRepository.findByAuthorId(safeAuthorId)
                .stream()
                .map(BookResponse::from)
                .toList();
    }

    @Transactional
    public BookResponse create(BookRequest request) {
        log.info(
                "Buch wird angelegt: title={}, authorId={}",
                request.title(),
                request.authorId()
        );

        ensureIsbnAvailable(request.isbn());
        AuthorEntity author = findAuthorEntity(request.authorId());

        BookEntity entity = new BookEntity(
                request.title(),
                author,
                request.isbn()
        );

        BookEntity savedEntity = bookRepository.save(entity);
        bookMetrics.bookCreated();

        log.info(
                "Buch wurde angelegt: bookId={}, title={}",
                savedEntity.getId(),
                savedEntity.getTitle()
        );

        return BookResponse.from(savedEntity);
    }

    @Transactional
    public BookResponse update(Long id, BookRequest request) {
        BookEntity entity = findBookEntity(id);
        AuthorEntity author = findAuthorEntity(request.authorId());

        if (!entity.getIsbn().equals(request.isbn())) {
            ensureIsbnAvailable(request.isbn());
        }

        entity.update(
                request.title(),
                author,
                request.isbn()
        );

        log.info(
                "Buch aktualisiert: bookId={}, authorId={}",
                id,
                request.authorId()
        );

        return BookResponse.from(entity);
    }

    @Transactional
    public void delete(Long id) {
        BookEntity entity = Objects.requireNonNull(findBookEntity(id), "entity");
        bookRepository.delete(entity);
    }

    private BookEntity findBookEntity(Long id) {
        Long safeId = Objects.requireNonNull(id, "id");

        return bookRepository.findById(safeId)
                .orElseThrow(() -> {
                    log.warn("Buch mit ID {} existiert nicht", safeId);
                    return new BookNotFoundException(safeId);
                });
    }

    private AuthorEntity findAuthorEntity(Long id) {
        Long safeId = Objects.requireNonNull(id, "id");

        return authorRepository.findById(safeId)
                .orElseThrow(() -> {
                    log.warn("Autor wurde nicht gefunden: authorId={}", safeId);
                    return new AuthorNotFoundException(safeId);
                });
    }

    private void ensureIsbnAvailable(String isbn) {
        if (bookRepository.existsByIsbn(isbn)) {
            throw new DuplicateIsbnException(isbn);
        }
    }
}

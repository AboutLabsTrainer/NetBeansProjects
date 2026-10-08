package com.example.bookshop.book;

import com.example.bookshop.author.AuthorNotFoundException;
import com.example.bookshop.author.AuthorResponse;
import com.example.bookshop.error.GlobalExceptionHandler;
import com.example.bookshop.security.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
@Import({SecurityConfig.class, GlobalExceptionHandler.class})
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookService bookService;

    private static BookResponse sampleBook(
            Long id,
            String title,
            Long authorId,
            String authorName,
            String isbn
    ) {
        return new BookResponse(id, title, new AuthorResponse(authorId, authorName), isbn);
    }

    @Test
    void getBooksWithoutAuthenticationReturns401() throws Exception {
        mockMvc.perform(get("/books"))
                .andExpect(status().isUnauthorized());

        verifyNoInteractions(bookService);
    }

    @Test
    void readerCanReadBooks() throws Exception {
        when(bookService.findAll()).thenReturn(List.of());

        mockMvc.perform(get("/books").with(httpBasic("reader", "reader-pass")))
                .andExpect(status().isOk());

        verify(bookService).findAll();
    }

    @Test
    void readerCannotCreateBook() throws Exception {
        mockMvc.perform(post("/books")
                        .with(httpBasic("reader", "reader-pass"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"title":"Effective Java","authorId":1,"isbn":"978-0-13-468599-1"}
                                """))
                .andExpect(status().isForbidden());

        verifyNoInteractions(bookService);
    }

    @Test
    void adminCanCreateBook() throws Exception {
        when(bookService.create(any(BookRequest.class)))
                .thenReturn(sampleBook(10L, "Effective Java", 1L, "Joshua Bloch", "978-0-13-468599-1"));

        mockMvc.perform(post("/books")
                        .with(httpBasic("admin", "admin-pass"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"title":"Effective Java","authorId":1,"isbn":"978-0-13-468599-1"}
                                """))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/books/10"));

        verify(bookService).create(any(BookRequest.class));
    }

    @Test
    void getAllBooks_returnsList() throws Exception {
        when(bookService.findAll()).thenReturn(List.of(
                sampleBook(1L, "Spring Boot lernen", 1L, "Anna Beispiel", "978-3-86680-192-9"),
                sampleBook(2L, "Modernes Java", 2L, "Max Mustermann", "978-0-13-468599-1")
        ));

        mockMvc.perform(get("/books").with(httpBasic("reader", "reader-pass")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Spring Boot lernen"))
                .andExpect(jsonPath("$[0].author.id").value(1))
                .andExpect(jsonPath("$[0].author.name").value("Anna Beispiel"))
                .andExpect(jsonPath("$[0].isbn").value("978-3-86680-192-9"))
                .andExpect(jsonPath("$[1].id").value(2));
    }

    @Test
    void getBookById_returnsBook() throws Exception {
        when(bookService.findById(1L))
                .thenReturn(sampleBook(1L, "Spring Boot lernen", 1L, "Anna Beispiel", "978-3-86680-192-9"));

        mockMvc.perform(get("/books/1").with(httpBasic("reader", "reader-pass")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Spring Boot lernen"))
                .andExpect(jsonPath("$.author.name").value("Anna Beispiel"))
                .andExpect(jsonPath("$.isbn").value("978-3-86680-192-9"));
    }

    @Test
    void getBookById_unknownId_returnsNotFoundProblemDetail() throws Exception {
        when(bookService.findById(99L))
                .thenThrow(new BookNotFoundException(99L));

        mockMvc.perform(get("/books/99").with(httpBasic("reader", "reader-pass")))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.title").value("Buch nicht gefunden"))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.detail").value("Buch mit ID 99 wurde nicht gefunden."))
                .andExpect(jsonPath("$.bookId").value(99));
    }

    @Test
    void findByAuthorId_returnsFilteredList() throws Exception {
        when(bookService.findByAuthorId(1L))
                .thenReturn(List.of(sampleBook(1L, "Spring Boot lernen", 1L, "Anna Beispiel", "978-3-86680-192-9")));

        mockMvc.perform(get("/books").param("authorId", "1").with(httpBasic("reader", "reader-pass")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].author.id").value(1))
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void findByAuthorId_unknownAuthor_returnsNotFound() throws Exception {
        when(bookService.findByAuthorId(999L))
                .thenThrow(new AuthorNotFoundException(999L));

        mockMvc.perform(get("/books").param("authorId", "999").with(httpBasic("reader", "reader-pass")))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.title").value("Autor nicht gefunden"))
                .andExpect(jsonPath("$.authorId").value(999));
    }

    @Test
    void createBook_returnsCreatedWithLocation() throws Exception {
        when(bookService.create(any(BookRequest.class)))
                .thenReturn(sampleBook(3L, "Clean Code", 1L, "Robert C. Martin", "978-0-13-235088-4"));

        mockMvc.perform(post("/books")
                        .with(httpBasic("admin", "admin-pass"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"title":"Clean Code","authorId":1,"isbn":"978-0-13-235088-4"}
                                """))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/books/3"))
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.title").value("Clean Code"))
                .andExpect(jsonPath("$.author.name").value("Robert C. Martin"))
                .andExpect(jsonPath("$.isbn").value("978-0-13-235088-4"));
    }

    @Test
    void createBook_unknownAuthor_returnsNotFound() throws Exception {
        when(bookService.create(any(BookRequest.class)))
                .thenThrow(new AuthorNotFoundException(999L));

        mockMvc.perform(post("/books")
                        .with(httpBasic("admin", "admin-pass"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"title":"Unbekanntes Buch","authorId":999,"isbn":"978-0-13-235088-4"}
                                """))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.title").value("Autor nicht gefunden"))
                .andExpect(jsonPath("$.authorId").value(999));
    }

    @Test
    void createBook_duplicateIsbn_returnsConflict() throws Exception {
        when(bookService.create(any(BookRequest.class)))
                .thenThrow(new DuplicateIsbnException("978-3-86680-192-9"));

        mockMvc.perform(post("/books")
                        .with(httpBasic("admin", "admin-pass"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"title":"Duplikat","authorId":1,"isbn":"978-3-86680-192-9"}
                                """))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.title").value("ISBN bereits vergeben"))
                .andExpect(jsonPath("$.status").value(409))
                .andExpect(jsonPath("$.isbn").value("978-3-86680-192-9"));
    }

    @Test
    void createBook_invalidBody_returnsValidationProblemDetail() throws Exception {
        mockMvc.perform(post("/books")
                        .with(httpBasic("admin", "admin-pass"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"title":" ","authorId":null,"isbn":null}
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").value("Validierungsfehler"))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.detail").value("Die übermittelten Buchdaten sind ungültig."))
                .andExpect(jsonPath("$.errors.title").value("Der Titel darf nicht leer sein."))
                .andExpect(jsonPath("$.errors.authorId").value("Die Autor-ID ist erforderlich."))
                .andExpect(jsonPath("$.errors.isbn").value("Die ISBN darf nicht leer sein."));
    }

    @Test
    void createBook_unreadableJson_returnsBadRequest() throws Exception {
        mockMvc.perform(post("/books")
                        .with(httpBasic("admin", "admin-pass"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"title":"Spring Boot","authorId":}
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").value("Ungültiges JSON"))
                .andExpect(jsonPath("$.status").value(400));
    }

    @Test
    void updateBook_returnsOk() throws Exception {
        when(bookService.update(eq(1L), any(BookRequest.class)))
                .thenReturn(sampleBook(1L, "Clean Code – Deutsche Ausgabe", 1L, "Robert C. Martin", "978-0-13-235088-4"));

        mockMvc.perform(put("/books/1")
                        .with(httpBasic("admin", "admin-pass"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"title":"Clean Code – Deutsche Ausgabe","authorId":1,"isbn":"978-0-13-235088-4"}
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Clean Code – Deutsche Ausgabe"));
    }

    @Test
    void updateBook_unknownId_returnsNotFound() throws Exception {
        when(bookService.update(eq(99L), any(BookRequest.class)))
                .thenThrow(new BookNotFoundException(99L));

        mockMvc.perform(put("/books/99")
                        .with(httpBasic("admin", "admin-pass"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"title":"X","authorId":1,"isbn":"978-0-13-235088-4"}
                                """))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.title").value("Buch nicht gefunden"))
                .andExpect(jsonPath("$.bookId").value(99));
    }

    @Test
    void deleteBook_returnsNoContent() throws Exception {
        doNothing().when(bookService).delete(1L);

        mockMvc.perform(delete("/books/1").with(httpBasic("admin", "admin-pass")))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteBook_unknownId_returnsNotFound() throws Exception {
        doThrow(new BookNotFoundException(99L))
                .when(bookService).delete(99L);

        mockMvc.perform(delete("/books/99").with(httpBasic("admin", "admin-pass")))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.title").value("Buch nicht gefunden"))
                .andExpect(jsonPath("$.bookId").value(99));
    }
}

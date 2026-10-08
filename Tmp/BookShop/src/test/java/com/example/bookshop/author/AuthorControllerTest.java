package com.example.bookshop.author;

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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthorController.class)
@Import({SecurityConfig.class, GlobalExceptionHandler.class})
class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuthorService authorService;

    @Test
    void getAuthorsWithoutAuthenticationReturns401() throws Exception {
        mockMvc.perform(get("/authors"))
                .andExpect(status().isUnauthorized());

        verifyNoInteractions(authorService);
    }

    @Test
    void readerCanReadAuthors() throws Exception {
        when(authorService.findAll()).thenReturn(List.of());

        mockMvc.perform(get("/authors").with(httpBasic("reader", "reader-pass")))
                .andExpect(status().isOk());

        verify(authorService).findAll();
    }

    @Test
    void readerCannotCreateAuthor() throws Exception {
        mockMvc.perform(post("/authors")
                        .with(httpBasic("reader", "reader-pass"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"name":"Robert C. Martin"}
                                """))
                .andExpect(status().isForbidden());

        verifyNoInteractions(authorService);
    }

    @Test
    void adminCanCreateAuthor() throws Exception {
        when(authorService.create(any(AuthorRequest.class)))
                .thenReturn(new AuthorResponse(1L, "Robert C. Martin"));

        mockMvc.perform(post("/authors")
                        .with(httpBasic("admin", "admin-pass"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"name":"Robert C. Martin"}
                                """))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/authors/1"));

        verify(authorService).create(any(AuthorRequest.class));
    }

    @Test
    void findAll_returnsList() throws Exception {
        when(authorService.findAll()).thenReturn(List.of(
                new AuthorResponse(1L, "Anna Beispiel"),
                new AuthorResponse(2L, "Max Mustermann")
        ));

        mockMvc.perform(get("/authors").with(httpBasic("reader", "reader-pass")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Anna Beispiel"))
                .andExpect(jsonPath("$[1].id").value(2));
    }

    @Test
    void create_returnsCreatedWithLocation() throws Exception {
        when(authorService.create(any(AuthorRequest.class)))
                .thenReturn(new AuthorResponse(1L, "Robert C. Martin"));

        mockMvc.perform(post("/authors")
                        .with(httpBasic("admin", "admin-pass"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"name":"Robert C. Martin"}
                                """))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/authors/1"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Robert C. Martin"));
    }
}

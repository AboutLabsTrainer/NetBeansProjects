package de.ibb.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("GreetingController Web Tests")
class GreetingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Nested
    @DisplayName("GET /greet/{name}")
    class GreetWithName {

        @Test
        @DisplayName("sollte 200 OK zurückgeben")
        void shouldReturn200OK() throws Exception {
            mockMvc.perform(get("/greet/Petra"))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("sollte korrekte Begrüßung zurückgeben")
        void shouldReturnCorrectGreeting() throws Exception {
            mockMvc.perform(get("/greet/Petra"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Hello, Petra!"));
        }
    }

    @Nested
    @DisplayName("GET /greet")
    class GreetWorld {

        @Test
        @DisplayName("sollte 'World' als Default begrüßen")
        void shouldGreetWorldByDefault() throws Exception {
            mockMvc.perform(get("/greet"))
                    .andExpect(content().string("Hello, World!"));
        }
    }
}


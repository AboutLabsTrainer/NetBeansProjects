package de.ibb.boot.basic.di;

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
class GreetingControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void german() throws Exception {
        mockMvc.perform(get("/api/greet").param("name", "Ada"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hallo Ada"));
    }

    @Test
    void english() throws Exception {
        mockMvc.perform(get("/api/greet/en").param("name", "Ada"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello Ada"));
    }

}

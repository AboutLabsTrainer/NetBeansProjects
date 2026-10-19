package de.ibb.boot.ak.messaging;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void publishesOrder() throws Exception {
        mockMvc.perform(post("/api/orders").param("text", "A-100"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value("A-100"));
    }

    @Test
    void publishesInvoice() throws Exception {
        mockMvc.perform(post("/api/invoices").param("text", "R-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value("R-1"));
    }

}

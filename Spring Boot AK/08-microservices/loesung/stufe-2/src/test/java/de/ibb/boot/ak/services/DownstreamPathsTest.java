package de.ibb.boot.ak.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DownstreamPathsTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void usersPathAndEndpoint() throws Exception {
        assertEquals("http://localhost:8081/api/users", DownstreamPaths.users("http://localhost:8081/"));
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value("Ada"));
    }

    @Test
    void ordersPathAndEndpoint() throws Exception {
        assertEquals("http://localhost:8082/api/orders", DownstreamPaths.orders("http://localhost:8082"));
        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value("A-100"));
    }

}

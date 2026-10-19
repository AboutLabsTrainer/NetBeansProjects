package de.ibb.boot.basic.scopes;

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
class ScopeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void countsByScope() throws Exception {
        mockMvc.perform(get("/api/scopes"))
                .andExpect(status().isOk())
                .andExpect(content().string("singleton=1,prototype=1,request=1"));
        mockMvc.perform(get("/api/scopes"))
                .andExpect(content().string("singleton=2,prototype=1,request=1"));
    }
}

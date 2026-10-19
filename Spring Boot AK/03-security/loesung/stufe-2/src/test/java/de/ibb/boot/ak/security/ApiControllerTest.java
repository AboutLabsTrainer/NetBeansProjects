package de.ibb.boot.ak.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ApiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void publicIsOpen() throws Exception {
        mockMvc.perform(get("/api/public"))
                .andExpect(status().isOk())
                .andExpect(content().string("offen"));
    }

    @Test
    void meNeedsLogin() throws Exception {
        mockMvc.perform(get("/api/me")).andExpect(status().isUnauthorized());
    }

    @Test
    void adaSeesHerName() throws Exception {
        mockMvc.perform(get("/api/me").with(httpBasic("ada", "secret")))
                .andExpect(status().isOk())
                .andExpect(content().string("ada"));
    }

    @Test
    void userCannotOpenAdmin() throws Exception {
        mockMvc.perform(get("/api/admin").with(httpBasic("ada", "secret")))
                .andExpect(status().isForbidden());
    }

    @Test
    void adminOpensAdmin() throws Exception {
        mockMvc.perform(get("/api/admin").with(httpBasic("admin", "secret")))
                .andExpect(status().isOk())
                .andExpect(content().string("admin"));
    }

    @Test
    void policyKnowsAdmin() {
        assertTrue(AccessPolicy.canOpenAdmin(java.util.Set.of("ADMIN")));
        assertFalse(AccessPolicy.canOpenAdmin(java.util.Set.of("USER")));
    }

}

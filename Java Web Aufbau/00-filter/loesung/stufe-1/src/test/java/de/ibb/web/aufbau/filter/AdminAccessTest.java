package de.ibb.web.aufbau.filter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AdminAccessTest {
    @Test
    void protectsAdminOnly() {
        assertEquals(AdminAccess.Decision.REDIRECT_LOGIN, AdminAccess.decide("/admin/home", false));
        assertEquals(AdminAccess.Decision.CONTINUE, AdminAccess.decide("/admin/home", true));
        assertEquals(AdminAccess.Decision.CONTINUE, AdminAccess.decide("/home", false));
    }
}

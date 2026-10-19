package de.ibb.web.aufbau.relationen;

import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

class ProfileLinkTest {
    @Test
    void bindsBothSides() {
        User user = new User("ada");
        UserProfile profile = new UserProfile("Bonn");
        ProfileLink.bind(user, profile);
        assertSame(profile, user.getProfile());
        assertSame(user, profile.getUser());
    }
}

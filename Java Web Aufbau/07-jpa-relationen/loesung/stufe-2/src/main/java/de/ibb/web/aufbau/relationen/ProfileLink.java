package de.ibb.web.aufbau.relationen;

public final class ProfileLink {
    private ProfileLink() {}

    public static void bind(User user, UserProfile profile) {
        if (user == null || profile == null) {
            throw new IllegalArgumentException();
        }
        user.setProfile(profile);
        profile.setUser(user);
    }
}

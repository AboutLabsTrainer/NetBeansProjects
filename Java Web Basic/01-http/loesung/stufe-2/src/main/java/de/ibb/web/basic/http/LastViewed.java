package de.ibb.web.basic.http;

public final class LastViewed {
    private LastViewed() {}

    public static Integer remember(Integer current, int requested) {
        if (requested > 0) {
            return requested;
        }
        return current;
    }
}

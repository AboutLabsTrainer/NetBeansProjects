package de.ibb.web.aufbau.listener;

public final class RequestBucket {
    private RequestBucket() {}

    public static String of(long millis) {
        if (millis < 100) {
            return "FAST";
        }
        if (millis < 500) {
            return "OK";
        }
        return "SLOW";
    }
}

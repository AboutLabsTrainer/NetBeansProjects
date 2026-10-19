package de.ibb.web.aufbau.listener;

public final class RequestBucket {
    private RequestBucket() {}

    public static String of(long millis) {
        // TODO: unter 100 FAST, unter 500 OK, sonst SLOW.
        throw new UnsupportedOperationException("TODO");
    }
}

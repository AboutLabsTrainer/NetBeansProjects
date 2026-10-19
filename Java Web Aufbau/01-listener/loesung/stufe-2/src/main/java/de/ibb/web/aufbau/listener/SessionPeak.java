package de.ibb.web.aufbau.listener;

public final class SessionPeak {
    private SessionPeak() {}

    public static int peak(int previousPeak, int active) {
        return Math.max(previousPeak, active);
    }
}

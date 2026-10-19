package de.ibb.logging;

import java.time.LocalDateTime;

/**
 * Ein einzelner Request-Log-Eintrag (für Anzeige im Admin-Bereich).
 */
public class RequestLogEntry {

    private final LocalDateTime timestamp;
    private final String method;
    private final String url;
    private final int status;
    private final long durationMs;
    private final boolean slow;

    public RequestLogEntry(LocalDateTime timestamp, String method, String url,
                           int status, long durationMs) {
        this.timestamp = timestamp;
        this.method = method;
        this.url = url;
        this.status = status;
        this.durationMs = durationMs;
        this.slow = durationMs > 500;
    }

    public LocalDateTime getTimestamp() { return timestamp; }
    public String getMethod() { return method; }
    public String getUrl() { return url; }
    public int getStatus() { return status; }
    public long getDurationMs() { return durationMs; }
    public boolean isSlow() { return slow; }
}

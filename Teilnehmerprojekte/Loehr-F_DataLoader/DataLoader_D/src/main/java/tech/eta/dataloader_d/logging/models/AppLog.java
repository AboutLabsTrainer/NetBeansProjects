package tech.eta.dataloader_d.logging.models;

import org.jspecify.annotations.NonNull;

import java.time.LocalDateTime;

// Do we need to walk back on NonNull if/when we connect against the database?
public record AppLog(
        @NonNull LocalDateTime timestamp,
        @NonNull LogLevel level,
        @NonNull String message
) {
}

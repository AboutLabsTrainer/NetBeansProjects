package tech.eta.dataloader_d.logging.models;

import lombok.Getter;

public enum LogLevel {
    INFO(1),
    NOTE(2),
    WARN(3),
    ERROR(4);
    // should we add FATAL? should we add DEBUG?

    @Getter
    private final int priority;

    LogLevel(int priority) {this.priority = priority;}

}

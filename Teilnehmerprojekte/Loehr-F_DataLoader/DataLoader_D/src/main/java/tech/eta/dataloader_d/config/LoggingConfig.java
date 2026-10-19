package tech.eta.dataloader_d.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import tech.eta.dataloader_d.logging.RealLogAppender;

@Configuration
public class LoggingConfig {

    private final RealLogAppender appender;

    public LoggingConfig(RealLogAppender appender) {
        this.appender = appender;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void registerAppender() {
        appender.registerWithRootLogger();
    }
}

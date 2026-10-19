package tech.eta.dataloader_d.logging;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import org.jspecify.annotations.NonNull;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
//import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import tech.eta.dataloader_d.logging.repositories.LogList;
import tech.eta.dataloader_d.logging.models.AppLog;
import tech.eta.dataloader_d.logging.models.LogLevel;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Stream;

@Component
public class RealLogAppender extends AppenderBase<ILoggingEvent> {

    public static final Marker NOTE_MARKER = MarkerFactory.getMarker("NOTE");

    private final SimpMessagingTemplate messagingTemplate; // for sending to the websocket...
    // Todo: check for concurrency stability... as with all spring templates

    private final LogList logList;

    private volatile boolean registered;

    public RealLogAppender(SimpMessagingTemplate messagingTemplate, LogList logList) {
        this.messagingTemplate = messagingTemplate;
//        this.messagingTemplate = new SimpMessagingTemplate(messageChannel); // Todo: read up on configuring the messageChannel
        this.logList = logList;
    }

    public synchronized void registerWithRootLogger() {
        if (registered) return;

        Logger rootLogger = (Logger) org.slf4j.LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);

        setContext(rootLogger.getLoggerContext());
        setName("REAL_LOG_APPENDER");
        start();

        rootLogger.addAppender(this);

        registered = true;
    }

    @Override
    protected void append(@NonNull ILoggingEvent event) {
        // Note: this obviously blocks external logs from showing.
        // Todo: should we make this a tag in the log instead? Or keep a second list for them?
        String loggerName = event.getLoggerName();
        if (Stream.of("tech.eta.dl_small", "tech.eta.dataloader").noneMatch(loggerName::startsWith)) return;

        AppLog log = new AppLog(
                LocalDateTime.ofInstant(Instant.ofEpochMilli(event.getTimeStamp()), ZoneId.systemDefault()),
                convertLevel(event),
                event.getFormattedMessage());

        logList.addLog(log);
        synchronized (this) { // TODO: read up on synchronized
            messagingTemplate.convertAndSend("/topic/logs", log);
            // TODO: read up on generating my own instances of the messaging template.
        }
    }

    // TOdo: can we make this more generic? surely there's a cleaner way...
    private @NonNull LogLevel convertLevel(@NonNull ILoggingEvent event) {
        List<Marker> markers = event.getMarkerList();

        if (markers != null && markers.stream().anyMatch(marker -> "NOTE".equals(marker.getName()))) {
            return LogLevel.NOTE;
        }

        if (event.getLevel().isGreaterOrEqual(Level.ERROR)) {
            return LogLevel.ERROR;
        }

        if (event.getLevel().isGreaterOrEqual(Level.WARN)) {
            return LogLevel.WARN;
        }

        return LogLevel.INFO;
    }

    public List<AppLog> findAll() {
        return logList.findAll();
    }
    public List<AppLog> getRecent() {
        return logList.getRecent();
    }
    public List<AppLog> getRecent(int number) {
        return logList.getRecent(number);
    }
}

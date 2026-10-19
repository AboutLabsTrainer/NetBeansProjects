package tech.eta.dataloader_d.logging;

import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tech.eta.dataloader_d.logging.models.AppLog;
import tech.eta.dataloader_d.logging.models.LogLevel;

import java.util.List;
import java.util.stream.Stream;


@Service
public class LogService {

    private static final Logger logger = LoggerFactory.getLogger(LogService.class);

    private final RealLogAppender appender; // apparently this is not the same one?

    public LogService(RealLogAppender appender) {
        this.appender = appender;
    }

    public void create(LogLevel level, String message) {
        switch (level) {
            case INFO -> logger.info(message);
            case NOTE -> logger.info(RealLogAppender.NOTE_MARKER, message);
            case WARN -> logger.warn(message);
            case ERROR -> logger.error(message);
        }
    }

    public @NonNull List<AppLog> recent() {
        return appender.getRecent();
    }

    public @NonNull List<AppLog> all() {
        return appender.findAll();
    }

    public @NonNull List<AppLog> list(
            boolean getAll, @NonNull List<LogLevel> levels, @NonNull String message, boolean ignoreCase) {
        // TODO: pass down pagination and filtering to RealLogAppender and further down.
//        System.out.println("logService.list"+all());
//        Stream<AppLog> logStream = Stream.empty();
//        if (getAll) {logStream = all().stream().filter(appLog -> levels.contains(appLog.level()));}
//        else {logStream = recent().stream().filter(appLog -> levels.contains(appLog.level()));}
        Stream<AppLog> logStream = (getAll ? all().stream() : recent().stream());
        if (!levels.isEmpty()) logStream = logStream.filter(appLog -> levels.contains(appLog.level()));
        if (message.isEmpty()) return logStream.toList();
        if (!ignoreCase) return logStream.filter(appLog -> appLog.message().contains(message)).toList();
        return logStream.filter(appLog -> appLog.message().toLowerCase().contains(message.toLowerCase())).toList();
    }

    public @NonNull List<AppLog> getRecent() {
        return appender.getRecent();
    }

    public @NonNull List<AppLog> getRecent(int number) {
        return appender.getRecent(number);
    }

    public @NonNull List<AppLog> findAll() {
        return appender.findAll();
    }
}

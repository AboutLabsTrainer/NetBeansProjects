package tech.eta.dataloader_d.logging.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.jspecify.annotations.NonNull;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "applogs")
@Getter
public class DBLog {
    @Id // IDENTITY doesn't allow batch insertions, SEQUENCE does
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GenIDs_eta_dataloader_DBLog")
    //@SequenceGenerator(
    //        name = "GenIDs_eta_dataloader_DBLog",
    //        sequenceName = "IDs_eta_dataloader_DBLog",
    //        allocationSize = 100
    //)
    @Column(unique = true, nullable = false)
    private Long id;

    @Setter
    @Column(nullable = false)
    private @NonNull LocalDateTime timestamp;

    @Setter
    @Column(nullable = false)
    @Enumerated(EnumType.STRING) // this way we store the string, not the ordinal
    private @NonNull LogLevel level;

    @Setter
    @Column(nullable = false)
    private @NonNull String message;

    // Converter-Methods
    public static @NonNull DBLog fromAppLog(@NonNull AppLog appLog) {
        DBLog log = new DBLog();
        log.setTimestamp(appLog.timestamp());
        log.setLevel(appLog.level());
        log.setMessage(appLog.message());
        return log;
    }

    public static @NonNull AppLog toAppLog(@NonNull DBLog dbLog) {
        return new AppLog(dbLog.getTimestamp(), dbLog.getLevel(), dbLog.getMessage());
    }

    // how to name these? convertDBLogs2AppLogs ? DBLogs2AppLogs
    public static @NonNull List<AppLog> toAppLogs(@NonNull List<DBLog> dbLogs) {
        return dbLogs.stream().map(DBLog::toAppLog).toList(); // .collect(Collectors.toSet());
    }

    public static @NonNull List<DBLog> fromAppLogs(@NonNull List<AppLog> appLogs) {
        return appLogs.stream().map(DBLog::fromAppLog).toList();
    }
}

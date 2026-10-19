package tech.eta.dataloader_d.logging.repositories;


import org.jspecify.annotations.NonNull;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import tech.eta.dataloader_d.logging.models.DBLog;
import tech.eta.dataloader_d.logging.models.AppLog;

import java.util.List;

@Repository
@ConditionalOnProperty(name = "app.auth.mode", havingValue = "database", matchIfMissing = true)
public class LogListDB extends LogList {
    private final AppLogRepository appLogRepository;

    public LogListDB(AppLogRepository appLogRepository) {
        this.appLogRepository = appLogRepository;
    }

    @Override
    public void addLog(@NonNull AppLog appLog) {
        super.addLog(appLog);
        appLogRepository.save(DBLog.fromAppLog(appLog));
    }

    // TODO: look into paging => load on demand
    @Override
    public @NonNull List<AppLog> findAll() {
//        return appLogRepository.findAll().stream().map(DBLog::toAppLog).toList();
        return DBLog.toAppLogs(appLogRepository.findAll());
    }

    // Should we use this to fill our recent logs from DB after restarting the app?
    public void fillFromDB() {
        this.logs.clear();
        List<DBLog> dbLogs = appLogRepository.findAll();
        this.logs.addAll(
                DBLog.toAppLogs(dbLogs.subList(Math.max(0, dbLogs.size() - LOGS_MAX), dbLogs.size()))
        );
    }
}

package tech.eta.dataloader_d.logging.repositories;

import org.jspecify.annotations.NonNull;
import tech.eta.dataloader_d.logging.models.AppLog;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class LogList {
    int LOGS_MAX = 100; // cache at most that many logs.
    final List<AppLog> logs = new CopyOnWriteArrayList<>();

    // fetch ALL logs
    public @NonNull List<AppLog> findAll() {return getRecent(LOGS_MAX);};

    // fetch recent logs
    public @NonNull List<AppLog> getRecent(){return List.copyOf(logs);}
    public @NonNull List<AppLog> getRecent(int number){
        return List.copyOf(logs.subList( Math.max(0, logs.size() - number), logs.size() ));
    }

    // append log
    public void addLog(@NonNull AppLog appLog){
        if (logs.size() >= this.LOGS_MAX) logs.remove(0);
        logs.add(appLog);
    };
}

package tech.eta.dataloader_d.logging.repositories;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

@Repository
// Wird NUR geladen, wenn app.auth.mode=json konfiguriert ist
@ConditionalOnProperty(name = "app.auth.mode", havingValue = "json")
public class LogListMem extends LogList {
//    @Override
//    public void addLog(AppLog appLog) {
//        logs.add(appLog);
//    }

//    @Override
//    public List<AppLog> findAll() {return List.copyOf(logs); }
//
//    @Override
//    public List<AppLog> getRecent(int number) {
//        return findAll();
//    }
}

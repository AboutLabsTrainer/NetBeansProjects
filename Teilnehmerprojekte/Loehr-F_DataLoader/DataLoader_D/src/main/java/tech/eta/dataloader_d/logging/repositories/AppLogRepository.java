package tech.eta.dataloader_d.logging.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.eta.dataloader_d.logging.models.DBLog;
import tech.eta.dataloader_d.logging.models.LogLevel;

import java.util.Optional;

public interface AppLogRepository extends JpaRepository<DBLog, Long> {
    Optional<DBLog> findByLevel(LogLevel level);
}

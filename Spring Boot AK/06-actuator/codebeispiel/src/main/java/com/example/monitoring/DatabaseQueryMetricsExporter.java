package com.example.monitoring;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Schreibt die Zähler des Query-Trackers periodisch als Micrometer-Gauges.
 * Scheduling ist an der Anwendung aktiv. Prometheus liest die Gauges über den Metrik-Endpunkt.
 */
@Component
@RequiredArgsConstructor
public class DatabaseQueryMetricsExporter {

    private final DatabaseQueryTracker queryTracker;
    private final MeterRegistry meterRegistry;

    @Scheduled(fixedRate = 15000)
    public void exportMetrics() {
        queryTracker.getQueryStatistics().forEach((key, stats) -> {
            Gauge.builder("database.query.count", stats, s -> (double) s.getTotalCount().get())
                    .tag("query", key)
                    .strongReference(true)
                    .register(meterRegistry);

            Gauge.builder("database.query.avg_duration", stats, DatabaseQueryTracker.QueryStatistics::getAverageDuration)
                    .tag("query", key)
                    .strongReference(true)
                    .register(meterRegistry);
        });
    }
}

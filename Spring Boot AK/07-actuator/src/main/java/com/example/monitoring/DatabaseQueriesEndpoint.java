package com.example.monitoring;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Eigener Actuator-Endpunkt mit den gesammelten Repository-Aufrufen.
 * Lesen und Löschen sind Operationen des Endpunkts. Die Security-Kette schützt ihn wie die übrigen internen Endpunkte.
 */
@Component
@Endpoint(id = "database-queries")
@RequiredArgsConstructor
public class DatabaseQueriesEndpoint {

    private final DatabaseQueryTracker queryTracker;

    @ReadOperation
    public DatabaseQueriesInfo getDatabaseQueries() {
        Map<String, DatabaseQueryTracker.QueryStatistics> stats = queryTracker.getQueryStatistics();
        List<DatabaseQueryTracker.QueryExecution> recent = queryTracker.getRecentQueries();

        List<SlowQuery> slowQueries = stats.values().stream()
                .map(s -> new SlowQuery(
                        s.getRepository() + "." + s.getMethod(),
                        s.getAverageDuration(),
                        s.getMaxDuration(),
                        s.getTotalCount().get()
                ))
                .sorted(Comparator.comparingDouble(SlowQuery::avgDuration).reversed())
                .limit(10)
                .collect(Collectors.toList());

        List<PopularQuery> popularQueries = stats.values().stream()
                .map(s -> new PopularQuery(
                        s.getRepository() + "." + s.getMethod(),
                        s.getTotalCount().get(),
                        s.getAverageDuration()
                ))
                .sorted(Comparator.comparingLong(PopularQuery::count).reversed())
                .limit(10)
                .collect(Collectors.toList());

        long totalQueries = stats.values().stream()
                .mapToLong(s -> s.getTotalCount().get())
                .sum();

        long totalErrors = stats.values().stream()
                .mapToLong(s -> s.getErrorCount().get())
                .sum();

        double overallErrorRate = totalQueries > 0
                ? (double) totalErrors / totalQueries * 100
                : 0;

        return new DatabaseQueriesInfo(
                totalQueries,
                totalErrors,
                overallErrorRate,
                stats.size(),
                slowQueries,
                popularQueries,
                recent
        );
    }

    @ReadOperation
    public Map<String, Object> getQueryDetails(@Selector String repository) {
        return queryTracker.getQueryStatistics().entrySet().stream()
                .filter(e -> e.getKey().startsWith(repository))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> Map.of(
                                "count", e.getValue().getTotalCount().get(),
                                "avgDuration", e.getValue().getAverageDuration(),
                                "maxDuration", e.getValue().getMaxDuration(),
                                "minDuration", e.getValue().getMinDuration() == Long.MAX_VALUE
                                        ? 0
                                        : e.getValue().getMinDuration(),
                                "errorRate", e.getValue().getErrorRate()
                        )
                ));
    }

    @DeleteOperation
    public Map<String, String> resetStatistics() {
        queryTracker.reset();
        return Map.of("status", "Statistics reset successfully");
    }

    /**
     * Antwortkörper des Endpunkts database-queries mit langsamen und häufigen Abfragen.
     */
    public record DatabaseQueriesInfo(
            long totalQueries,
            long totalErrors,
            double overallErrorRate,
            int uniqueQueries,
            List<SlowQuery> slowQueries,
            List<PopularQuery> popularQueries,
            List<DatabaseQueryTracker.QueryExecution> recentQueries
    ) {
    }

    /**
     * Eine langsame Repository-Methode in der Endpunkt-Antwort.
     */
    public record SlowQuery(String query, double avgDuration, long maxDuration, long count) {
    }

    /**
     * Eine häufige Repository-Methode in der Endpunkt-Antwort.
     */
    public record PopularQuery(String query, long count, double avgDuration) {
    }
}

package com.example.monitoring;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Sammelt Anzahl und Dauer der Repository-Aufrufe im Speicher.
 * Der Aspect schreibt hier hinein, Endpunkt und Metrik-Export lesen daraus.
 */
@Component
public class DatabaseQueryTracker {

    private final Map<String, QueryStatistics> queryStats = new ConcurrentHashMap<>();
    private final List<QueryExecution> recentQueries = Collections.synchronizedList(new ArrayList<>());
    private static final int MAX_RECENT_QUERIES = 100;

    public void recordQuery(String repository, String method, long durationMs, boolean success) {
        QueryStatistics stats = queryStats.computeIfAbsent(
                repository + "." + method,
                k -> new QueryStatistics(repository, method)
        );

        stats.incrementCount();
        stats.addDuration(durationMs);
        if (!success) {
            stats.incrementErrors();
        }

        QueryExecution execution = new QueryExecution(
                repository,
                method,
                durationMs,
                success,
                LocalDateTime.now()
        );

        synchronized (recentQueries) {
            recentQueries.add(execution);
            if (recentQueries.size() > MAX_RECENT_QUERIES) {
                recentQueries.remove(0);
            }
        }
    }

    public Map<String, QueryStatistics> getQueryStatistics() {
        return new HashMap<>(queryStats);
    }

    public List<QueryExecution> getRecentQueries() {
        synchronized (recentQueries) {
            return new ArrayList<>(recentQueries);
        }
    }

    public void reset() {
        queryStats.clear();
        recentQueries.clear();
    }

    /**
     * Aggregierte Zahl und Dauer einer Repository-Methode.
     */
    @Data
    public static class QueryStatistics {
        private final String repository;
        private final String method;
        private final AtomicLong totalCount = new AtomicLong(0);
        private final AtomicLong totalDuration = new AtomicLong(0);
        private final AtomicLong errorCount = new AtomicLong(0);
        private long minDuration = Long.MAX_VALUE;
        private long maxDuration = 0;

        public QueryStatistics(String repository, String method) {
            this.repository = repository;
            this.method = method;
        }

        public void incrementCount() {
            totalCount.incrementAndGet();
        }

        public synchronized void addDuration(long duration) {
            totalDuration.addAndGet(duration);
            minDuration = Math.min(minDuration, duration);
            maxDuration = Math.max(maxDuration, duration);
        }

        public void incrementErrors() {
            errorCount.incrementAndGet();
        }

        public double getAverageDuration() {
            long count = totalCount.get();
            return count > 0 ? (double) totalDuration.get() / count : 0;
        }

        public double getErrorRate() {
            long count = totalCount.get();
            return count > 0 ? (double) errorCount.get() / count * 100 : 0;
        }
    }

    /**
     * Ein einzelner gemessener Repository-Aufruf.
     */
    @Data
    public static class QueryExecution {
        private final String repository;
        private final String method;
        private final long durationMs;
        private final boolean success;
        private final LocalDateTime timestamp;
    }
}

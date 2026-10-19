package de.ibb.tag08.level3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Vergleicht eine sequentielle und eine parallele Ausführung.
 */
public class PerformanceComparisonDemo {

    private static final int TASK_COUNT = 8;

    @LogExecutionTime("Sequentielle Verarbeitung")
    public void runSequential() throws InterruptedException {
        for (int i = 0; i < TASK_COUNT; i++) {
            simulateWork(i);
        }
    }

    @LogExecutionTime("Parallele Verarbeitung")
    public void runParallel() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        try {
            List<Callable<Void>> tasks = new ArrayList<>();
            for (int i = 0; i < TASK_COUNT; i++) {
                int taskId = i;
                tasks.add(() -> {
                    simulateWork(taskId);
                    return null;
                });
            }
            List<Future<Void>> futures = executor.invokeAll(tasks);
            for (Future<Void> future : futures) {
                future.get(10, TimeUnit.SECONDS);
            }
        } finally {
            executor.shutdown();
        }
    }

    private void simulateWork(int id) throws InterruptedException {
        // Simuliert CPU- und I/O-arbeit
        long sum = 0;
        for (int i = 0; i < 100_000; i++) {
            sum += i * id;
        }
        Thread.sleep(100);
        if (id == -1 && sum == 0) {
            System.out.print(""); // nie erreicht, nur um Warnungen zu vermeiden
        }
    }
}


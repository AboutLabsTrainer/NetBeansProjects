package de.ibb.tag08.level2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Führt die Beispiele für Level 2 aus:
 * - @Validate-Annotation und Validator
 * - Thread-Pool mit 10 Aufgaben
 * - Callable/Future zur Ergebnisverarbeitung.
 */
public class Level2Demo {

    public static void run() throws Exception {
        demoValidation();
        demoThreadPoolWithTasks();
        demoCallableAndFuture();
    }

    private static void demoValidation() {
        System.out.println("[Level2] Validierung mit @Validate:");

        Person p1 = new Person("", -5);
        Person p2 = new Person("Alice", 30);

        printValidationResult("Person p1", Validator.validate(p1));
        printValidationResult("Person p2", Validator.validate(p2));
    }

    private static void printValidationResult(String label, List<String> messages) {
        System.out.println("  " + label + ":");
        if (messages.isEmpty()) {
            System.out.println("    OK");
        } else {
            for (String msg : messages) {
                System.out.println("    " + msg);
            }
        }
    }

    private static void demoThreadPoolWithTasks() throws InterruptedException {
        System.out.println("[Level2] Thread-Pool mit 10 Aufgaben:");

        ExecutorService executor = Executors.newFixedThreadPool(4);
        try {
            for (int i = 1; i <= 10; i++) {
                int taskId = i;
                executor.execute(() -> {
                    String threadName = Thread.currentThread().getName();
                    System.out.printf("  Aufgabe %d läuft in %s%n", taskId, threadName);
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    System.out.printf("  Aufgabe %d in %s fertig%n", taskId, threadName);
                });
            }
        } finally {
            executor.shutdown();
            executor.awaitTermination(10, TimeUnit.SECONDS);
        }
    }

    private static void demoCallableAndFuture() throws Exception {
        System.out.println("[Level2] Callable & Future – Ergebnisse sammeln:");

        ExecutorService executor = Executors.newFixedThreadPool(3);
        try {
            List<Callable<String>> tasks = new ArrayList<>();
            for (int i = 1; i <= 5; i++) {
                int taskId = i;
                tasks.add(() -> {
                    Thread.sleep(500L * taskId);
                    return "Ergebnis " + taskId + " von " + Thread.currentThread().getName();
                });
            }

            List<Future<String>> futures = executor.invokeAll(tasks);
            for (Future<String> future : futures) {
                String result = future.get(5, TimeUnit.SECONDS);
                System.out.println("  Future-Resultat: " + result);
            }
        } finally {
            executor.shutdown();
        }
    }
}


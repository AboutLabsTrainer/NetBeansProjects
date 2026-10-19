package de.ibb.tag08.level1;

import java.lang.reflect.Method;

/**
 * Führt die Beispiele für Level 1 aus:
 * - @Author-Annotation anzeigen
 * - Drei Threads starten, die zählen.
 */
public class Level1Demo {

    public static void run() throws Exception {
        showAuthorAnnotations();
        runCountingThreads();
    }

    private static void showAuthorAnnotations() throws Exception {
        System.out.println("[Level1] Auslesen von @Author-Annotationen:");

        Class<SimpleExample> clazz = SimpleExample.class;
        if (clazz.isAnnotationPresent(Author.class)) {
            Author author = clazz.getAnnotation(Author.class);
            System.out.printf("  Klasse %s von %s am %s%n",
                    clazz.getSimpleName(), author.name(), author.date());
        }

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Author.class)) {
                Author author = method.getAnnotation(Author.class);
                System.out.printf("  Methode %s von %s am %s%n",
                        method.getName(), author.name(), author.date());
            }
        }
    }

    private static void runCountingThreads() throws InterruptedException {
        System.out.println("[Level1] Starte drei Zähl-Threads:");

        Thread t1 = new Thread(new CountingTask("Thread-A"));
        Thread t2 = new Thread(new CountingTask("Thread-B"));
        Thread t3 = new Thread(new CountingTask("Thread-C"));

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();
    }
}


package de.ibb.tag08.level3;

/**
 * Führt die Beispiele für Level 3 aus:
 * - Logging-Annotation und -Processor
 * - Producer-Consumer mit ExecutorService
 * - Performancevergleich sequentiell vs. parallel.
 */
public class Level3Demo {

    public static void run() throws Exception {
        demoLoggingAndPerformance();
        demoProducerConsumer();
    }

    private static void demoLoggingAndPerformance() throws Exception {
        System.out.println("[Level3] Logging & Performance-Vergleich:");
        PerformanceComparisonDemo demo = new PerformanceComparisonDemo();
        LoggingProcessor.invokeWithLogging(demo);
    }

    private static void demoProducerConsumer() throws Exception {
        System.out.println("[Level3] Producer-Consumer mit ExecutorService:");
        ProducerConsumerDemo demo = new ProducerConsumerDemo();
        demo.runDemo();
    }
}


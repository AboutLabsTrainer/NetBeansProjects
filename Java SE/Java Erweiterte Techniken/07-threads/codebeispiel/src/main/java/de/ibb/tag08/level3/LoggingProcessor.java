package de.ibb.tag08.level3;

import java.lang.reflect.Method;

/**
 * Einfacher Runtime-"Processor", der Methoden mit {@link LogExecutionTime}
 * auf einem Zielobjekt ausführt und die Dauer misst.
 */
public class LoggingProcessor {

    public static void invokeWithLogging(Object target) throws Exception {
        Class<?> clazz = target.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            if (!method.isAnnotationPresent(LogExecutionTime.class)) {
                continue;
            }
            LogExecutionTime annotation = method.getAnnotation(LogExecutionTime.class);
            if (!annotation.enabled()) {
                continue;
            }
            method.setAccessible(true);
            String label = annotation.value().isBlank() ? method.getName() : annotation.value();
            System.out.println("  Starte: " + label);
            long start = System.currentTimeMillis();
            method.invoke(target);
            long duration = System.currentTimeMillis() - start;
            System.out.println("  Dauer " + label + ": " + duration + " ms");
        }
    }
}


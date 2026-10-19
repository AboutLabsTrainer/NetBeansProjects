package com.example.helloworldapi.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Markiert eine Methode als Join-Point für den Logging-Aspect.
 * Die Annotation selbst enthält keine Logik. Der Aspect liest sie zur Laufzeit und schreibt den Aufruf ins Log.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Logged {
    String level() default "INFO";
}

package com.example.personmanagement.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Markiert Service-Methoden, deren Dauer der Logging-Aspect messen soll.
 * Die Annotation ist der Pointcut. Die Messung steht im Aspect.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogExecutionTime {
}


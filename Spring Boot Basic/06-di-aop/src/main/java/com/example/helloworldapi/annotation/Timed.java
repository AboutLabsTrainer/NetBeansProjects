package com.example.helloworldapi.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Markiert eine Methode, deren Laufzeit der Performance-Aspect messen soll.
 * Ohne diese Markierung bleibt der Aufruf außerhalb dieses Around-Advices.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Timed {
}

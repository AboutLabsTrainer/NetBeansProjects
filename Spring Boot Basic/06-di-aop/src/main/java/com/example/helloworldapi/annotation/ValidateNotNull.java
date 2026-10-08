package com.example.helloworldapi.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Markiert eine Methode, deren Argumente der Validation-Aspect vor dem Aufruf prüft.
 * Welche Parameter gemeint sind, steht an der Annotation. Die Prüfung liegt im Aspect, nicht in der Methode.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateNotNull {
    int[] parameterIndices() default {};
}

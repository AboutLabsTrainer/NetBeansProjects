package de.ibb.tag08.level2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Allgemeine Validierungs-Annotation für Felder.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Validate {

    boolean notEmpty() default false;

    int min() default Integer.MIN_VALUE;

    int max() default Integer.MAX_VALUE;

    String message() default "Ungültiger Wert";
}


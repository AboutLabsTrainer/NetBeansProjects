package com.example.helloworldapi.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * Eigene Constraint, die GermanPhoneValidator auslöst.
 * Die Prüfung gehört zu Bean Validation, nicht zum AOP-Pointcut.
 */
@Documented
@Constraint(validatedBy = GermanPhoneValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidGermanPhone {

    String message() default "Ungültige deutsche Telefonnummer";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

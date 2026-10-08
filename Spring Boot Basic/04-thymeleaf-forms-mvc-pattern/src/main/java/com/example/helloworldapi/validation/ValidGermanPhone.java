package com.example.helloworldapi.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * Eigene Constraint für deutsche Telefonnummern.
 * Bean Validation ruft damit den zugeordneten Validator auf, sobald das Formular geprüft wird.
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

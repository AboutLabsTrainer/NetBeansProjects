package com.example.helloworldapi.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * Constraint-Annotation für eine deutsche Telefonnummer.
 * Sie verknüpft das Feld mit GermanPhoneValidator.
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

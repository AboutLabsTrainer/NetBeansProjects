package com.example.helloworldapi.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Prüft den Wert einer eigenen Bean-Validation-Constraint.
 * Der Validator läuft, wenn ein Feld die zugehörige Annotation trägt, und meldet das Ergebnis an die Binding-Fehler.
 */
public class GermanPhoneValidator implements ConstraintValidator<ValidGermanPhone, String> {

    private static final String PATTERN = "^(\\+49|0)[1-9]\\d{1,14}$|^(\\+49|0)[1-9][\\d\\s\\-/]{4,20}$";

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext context) {
        if (phone == null || phone.trim().isEmpty()) {
            return true;
        }
        String cleaned = phone.replaceAll("[\\s\\-/]", "");
        return cleaned.matches(PATTERN);
    }
}

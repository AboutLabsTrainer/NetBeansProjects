package com.example.helloworldapi.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validator der eigenen Telefon-Constraint.
 * Er wird von Bean Validation aufgerufen, nicht vom Controller selbst.
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

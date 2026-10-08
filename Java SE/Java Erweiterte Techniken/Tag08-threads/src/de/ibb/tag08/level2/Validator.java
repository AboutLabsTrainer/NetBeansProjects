package de.ibb.tag08.level2;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Einfache Reflection-basierte Validierung für {@link Validate}.
 */
public class Validator {

    public static List<String> validate(Object obj) {
        List<String> messages = new ArrayList<>();
        Class<?> clazz = obj.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            if (!field.isAnnotationPresent(Validate.class)) {
                continue;
            }
            Validate validate = field.getAnnotation(Validate.class);
            field.setAccessible(true);
            try {
                Object value = field.get(obj);
                checkField(field.getName(), value, validate, messages);
            } catch (IllegalAccessException e) {
                messages.add("Feld " + field.getName() + " konnte nicht gelesen werden: " + e.getMessage());
            }
        }

        return messages;
    }

    private static void checkField(String fieldName, Object value, Validate validate, List<String> messages) {
        if (validate.notEmpty()) {
            if (value == null || (value instanceof String && ((String) value).isBlank())) {
                messages.add(fieldName + ": " + validate.message());
                return;
            }
        }

        if (value instanceof Number) {
            int intVal = ((Number) value).intValue();
            if (intVal < validate.min() || intVal > validate.max()) {
                messages.add(fieldName + ": " + validate.message()
                        + " (Wert=" + intVal + ", erlaubt " + validate.min()
                        + " bis " + validate.max() + ")");
            }
        }
    }
}


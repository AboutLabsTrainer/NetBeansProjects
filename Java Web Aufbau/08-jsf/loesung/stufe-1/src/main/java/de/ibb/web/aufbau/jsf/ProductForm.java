package de.ibb.web.aufbau.jsf;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public final class ProductForm {
    private ProductForm() {}

    public static List<String> errors(String name, BigDecimal price) {
        List<String> errors = new ArrayList<>();
        if (name == null || name.isBlank()) {
            errors.add("name");
        }
        if (price == null || price.signum() < 0) {
            errors.add("price");
        }
        return errors;
    }
}

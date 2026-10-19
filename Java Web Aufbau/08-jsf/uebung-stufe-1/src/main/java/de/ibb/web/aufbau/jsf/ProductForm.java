package de.ibb.web.aufbau.jsf;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public final class ProductForm {
    private ProductForm() {}

    public static List<String> errors(String name, BigDecimal price) {
        // TODO: "name" wenn leer, "price" wenn null oder negativ.
        throw new UnsupportedOperationException("TODO");
    }
}

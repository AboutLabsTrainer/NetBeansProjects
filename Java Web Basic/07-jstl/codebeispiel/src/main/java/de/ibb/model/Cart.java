package de.ibb.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Rechnet Zwischensumme, Steuer und Gesamtbetrag für die JSTL-Seite.
 */

public class Cart {
    private static final BigDecimal TAX_RATE = new BigDecimal("0.19");
    private static final int MONEY_SCALE = 2;
    private static final RoundingMode MONEY_ROUNDING = RoundingMode.HALF_UP;
    private final List<CartItem> items = new ArrayList<>();

    public void add(CartItem item) { items.add(item); }
    public List<CartItem> getItems() { return Collections.unmodifiableList(items); }

    public BigDecimal getSubtotal() { return money(rawSubtotal()); }

    public BigDecimal getTax() {
        return money(rawSubtotal().multiply(TAX_RATE));
    }

    public BigDecimal getTotal() {
        BigDecimal subtotal = rawSubtotal();
        BigDecimal roundedSubtotal = money(subtotal);
        BigDecimal tax = money(subtotal.multiply(TAX_RATE));
        return money(roundedSubtotal.add(tax));
    }

    private BigDecimal money(BigDecimal value) {
        return value.setScale(MONEY_SCALE, MONEY_ROUNDING);
    }

    private BigDecimal rawSubtotal() {
        return items.stream()
                .map(CartItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

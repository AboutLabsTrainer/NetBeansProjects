package de.ibb.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Eine Warenkorbposition mit Preis und Menge für die JSTL-Iteration.
 */

public class CartItem {
    private String productName;
    private BigDecimal price;
    private int quantity;

    public CartItem(String productName, BigDecimal price, int quantity) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getProductName() { return productName; }
    public BigDecimal getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public BigDecimal getSubtotal() { return price.multiply(BigDecimal.valueOf(quantity)); }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartItem)) return false;
        CartItem that = (CartItem) o;
        return quantity == that.quantity &&
               java.util.Objects.equals(productName, that.productName) &&
               java.util.Objects.equals(price, that.price);
    }
    @Override public int hashCode() { return Objects.hash(productName, price, quantity); }
}

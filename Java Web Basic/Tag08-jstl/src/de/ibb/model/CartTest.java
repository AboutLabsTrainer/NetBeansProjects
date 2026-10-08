package de.ibb.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class CartTest {

    @Test
    void taxIsRoundedToTwoDecimals() {
        Cart cart = new Cart();
        cart.add(new CartItem("Sticker Pack", new BigDecimal("0.05"), 1));

        assertEquals(new BigDecimal("0.05"), cart.getSubtotal());
        assertEquals(new BigDecimal("0.01"), cart.getTax());
        assertEquals(new BigDecimal("0.06"), cart.getTotal());
    }

    @Test
    void totalUsesRoundedValuesForMultipleItems() {
        Cart cart = new Cart();
        cart.add(new CartItem("E-Book", new BigDecimal("0.01"), 1));
        cart.add(new CartItem("Cheat Sheet", new BigDecimal("0.02"), 1));

        assertEquals(new BigDecimal("0.03"), cart.getSubtotal());
        assertEquals(new BigDecimal("0.01"), cart.getTax());
        assertEquals(new BigDecimal("0.04"), cart.getTotal());
    }
}


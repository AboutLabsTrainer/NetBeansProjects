package de.ibb.web.aufbau.relationen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

class OrderLinkTest {
    @Test
    void keepsBothSides() {
        User user = new User("ada");
        Order order = new Order();
        OrderLink.add(user, order);
        OrderLink.add(user, order);
        assertEquals(1, user.getOrders().size());
        assertSame(user, order.getUser());
    }
}

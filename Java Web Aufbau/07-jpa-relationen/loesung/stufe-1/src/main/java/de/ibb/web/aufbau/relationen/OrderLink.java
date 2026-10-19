package de.ibb.web.aufbau.relationen;

public final class OrderLink {
    private OrderLink() {}

    public static void add(User user, Order order) {
        if (user == null || order == null) {
            throw new IllegalArgumentException();
        }
        if (!user.getOrders().contains(order)) {
            user.getOrders().add(order);
        }
        order.setUser(user);
    }
}

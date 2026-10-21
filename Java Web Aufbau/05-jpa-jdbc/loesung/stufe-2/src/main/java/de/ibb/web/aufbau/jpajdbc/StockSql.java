package de.ibb.web.aufbau.jpajdbc;

import java.util.List;

public final class StockSql {
    private StockSql() {}

    public static BoundSql updateStock(long id, int stock) {
        return new BoundSql("UPDATE jwebak05_products SET stock = ? WHERE id = ?", List.of(stock, id));
    }
}

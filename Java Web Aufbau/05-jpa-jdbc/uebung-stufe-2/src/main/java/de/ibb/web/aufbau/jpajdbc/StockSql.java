package de.ibb.web.aufbau.jpajdbc;

public final class StockSql {
    private StockSql() {}

    public static BoundSql updateStock(long id, int stock) {
        // TODO: UPDATE jwebak05_products SET stock = ? WHERE id = ?
        // Erst der Bestand, dann die Id.
        throw new UnsupportedOperationException("TODO");
    }
}

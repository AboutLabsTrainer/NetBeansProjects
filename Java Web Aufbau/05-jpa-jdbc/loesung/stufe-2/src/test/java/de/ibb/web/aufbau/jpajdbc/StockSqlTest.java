package de.ibb.web.aufbau.jpajdbc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class StockSqlTest {
    @Test
    void bindsStockBeforeId() {
        BoundSql sql = StockSql.updateStock(9L, 4);
        assertEquals("UPDATE products SET stock = ? WHERE id = ?", sql.sql());
        assertEquals(List.of(4, 9L), sql.parameters());
    }
}

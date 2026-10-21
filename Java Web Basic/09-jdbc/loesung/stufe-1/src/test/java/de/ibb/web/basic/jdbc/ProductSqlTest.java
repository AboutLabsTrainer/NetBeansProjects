package de.ibb.web.basic.jdbc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class ProductSqlTest {
    @Test
    void bindsInsertParametersInOrder() {
        BoundSql sql = ProductSql.insert("Stift", 1.5d, 3);
        assertEquals("INSERT INTO jwebb09_products(name, price, stock) VALUES(?,?,?)", sql.sql());
        assertEquals(List.of("Stift", 1.5d, 3), sql.parameters());
    }
}

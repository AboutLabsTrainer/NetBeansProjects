package de.ibb.web.basic.jdbc;

import java.util.List;

public final class ProductSql {
    private ProductSql() {}

    public static BoundSql insert(String name, double price, int stock) {
        return new BoundSql(
                "INSERT INTO jwebb09_products(name, price, stock) VALUES(?,?,?)",
                List.of(name, price, stock));
    }
}

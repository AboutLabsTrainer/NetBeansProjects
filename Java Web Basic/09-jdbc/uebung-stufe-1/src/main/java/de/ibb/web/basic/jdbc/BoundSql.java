package de.ibb.web.basic.jdbc;

import java.util.List;

public final class BoundSql {
    private final String sql;
    private final List<Object> parameters;

    public BoundSql(String sql, List<Object> parameters) {
        this.sql = sql;
        this.parameters = List.copyOf(parameters);
    }

    public String sql() { return sql; }
    public List<Object> parameters() { return parameters; }
}

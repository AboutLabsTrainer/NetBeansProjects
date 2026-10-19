package de.ibb.jdbc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PersonenSqlTest {

    @Test
    void statementsWieImBeispiel() {
        String create = PersonenSql.createTableSql().replaceAll("\\s+", " ").trim();
        assertTrue(create.contains("CREATE TABLE personen"));
        assertTrue(create.contains("AUTO_INCREMENT"));
        assertTrue(create.contains("`alter`"));
        assertTrue(create.contains("email VARCHAR(100)"));
        assertEquals("INSERT INTO personen (name, `alter`, email) VALUES (?, ?, ?)", PersonenSql.insertSql());
        assertEquals("SELECT id, name, `alter`, email FROM personen", PersonenSql.selectAlleSql());
    }
}

package de.ibb.jdbc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Test;

class PersonenDaoTest {

    @Test
    void speichertInH2() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:mem:personen06;DB_CLOSE_DELAY=-1");
        PersonenDao dao = new PersonenDao(dataSource);
        dao.erstelleTabelle();
        int id = dao.speichere("Max Mustermann", 28, "max@example.com");
        assertEquals("Max Mustermann", dao.findeName(id));
        assertNull(dao.findeName(-1));
    }
}

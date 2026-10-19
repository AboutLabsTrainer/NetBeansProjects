package de.ibb.jdbc.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Test;

class PersonenDaoTest {

    @Test
    void saveLiefertIdUndZaehlt() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:mem:pool07;DB_CLOSE_DELAY=-1");
        PersonenDao dao = new PersonenDao(dataSource);
        dao.erstelleTabelle();
        Person gespeichert = dao.save(new Person("Tom Müller", 22, null));
        assertNotNull(gespeichert.id());
        assertEquals("Tom Müller", gespeichert.name());
        assertEquals(1, dao.count());
    }
}

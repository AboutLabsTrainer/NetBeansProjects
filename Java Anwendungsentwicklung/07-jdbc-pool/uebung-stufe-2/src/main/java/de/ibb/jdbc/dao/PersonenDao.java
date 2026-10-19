package de.ibb.jdbc.dao;

import javax.sql.DataSource;

public class PersonenDao {

    private final DataSource dataSource;

    public PersonenDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void erstelleTabelle() {
    }

    public Person save(Person person) {
        return null;
    }

    public long count() {
        return 0;
    }
}

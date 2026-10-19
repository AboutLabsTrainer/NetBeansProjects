package de.ibb.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.sql.DataSource;

/**
 * Kleines DAO zur Tabelle personen. Spalte lebensalter statt des MySQL-Spaltennamens alter,
 * damit die Übung auf H2 läuft. Das Codebeispiel spricht weiter MySQL an.
 */
public class PersonenDao {

    private final DataSource dataSource;

    public PersonenDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void erstelleTabelle() {
    }

    public int speichere(String name, int lebensalter, String email) {
        return 0;
    }

    public String findeName(int id) {
        return null;
    }
}

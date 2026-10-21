package de.ibb.jdbc.dao;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * PersonDaoJdbc - JDBC-Implementation des PersonDao Interface.
 * 
 * Tag 7 des Kurses "Java Anwendungsentwicklung"
 * 
 * Vorteile des DAO-Patterns:
 * - SQL ist nur HIER, nicht überall im Code verstreut
 * - Austauschbar: Heute JDBC, morgen JPA
 * - Testbar: Mock-DAO für Unit Tests
 */
public class PersonDaoJdbc implements PersonDao {
    
    private final DataSource dataSource;
    
    /**
     * Konstruktor mit Dependency Injection.
     * Der DataSource wird von außen übergeben (z.B. HikariCP Pool).
     */
    public PersonDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    // ══════════════════════════════════════════════════════════════
    // CRUD IMPLEMENTIERUNG
    // ══════════════════════════════════════════════════════════════
    
    @Override
    public Optional<Person> findById(int id) {
        String sql = "SELECT id, name, alter, email FROM jae07_personen WHERE id = ?";
        
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
            }
            
        } catch (SQLException e) {
            throw new DaoException("Fehler beim Laden von Person mit ID " + id, e);
        }
        
        return Optional.empty();
    }
    
    @Override
    public List<Person> findAll() {
        String sql = "SELECT id, name, alter, email FROM jae07_personen ORDER BY id";
        List<Person> result = new ArrayList<>();
        
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                result.add(mapRow(rs));
            }
            
        } catch (SQLException e) {
            throw new DaoException("Fehler beim Laden aller Personen", e);
        }
        
        return result;
    }
    
    @Override
    public List<Person> findByNameContaining(String name) {
        String sql = "SELECT id, name, alter, email FROM jae07_personen WHERE LOWER(name) LIKE ? ORDER BY name";
        List<Person> result = new ArrayList<>();
        
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, "%" + name.toLowerCase() + "%");
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.add(mapRow(rs));
                }
            }
            
        } catch (SQLException e) {
            throw new DaoException("Fehler bei der Suche nach '" + name + "'", e);
        }
        
        return result;
    }
    
    @Override
    public Person save(Person person) {
        String sql = "INSERT INTO jae07_personen (name, alter, email) VALUES (?, ?, ?)";
        
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, person.name());
            ps.setInt(2, person.alter());
            ps.setString(3, person.email());
            
            int affected = ps.executeUpdate();
            
            if (affected > 0) {
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        return person.withId(keys.getInt(1));
                    }
                }
            }
            
            throw new DaoException("Person konnte nicht gespeichert werden");
            
        } catch (SQLException e) {
            throw new DaoException("Fehler beim Speichern von " + person.name(), e);
        }
    }
    
    @Override
    public boolean update(Person person) {
        String sql = "UPDATE jae07_personen SET name = ?, alter = ?, email = ? WHERE id = ?";
        
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, person.name());
            ps.setInt(2, person.alter());
            ps.setString(3, person.email());
            ps.setInt(4, person.id());
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            throw new DaoException("Fehler beim Aktualisieren von Person " + person.id(), e);
        }
    }
    
    @Override
    public boolean deleteById(int id) {
        String sql = "DELETE FROM jae07_personen WHERE id = ?";
        
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            throw new DaoException("Fehler beim Löschen von Person " + id, e);
        }
    }
    
    @Override
    public long count() {
        String sql = "SELECT COUNT(*) FROM jae07_personen";
        
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                return rs.getLong(1);
            }
            return 0;
            
        } catch (SQLException e) {
            throw new DaoException("Fehler beim Zählen der Personen", e);
        }
    }
    
    // ══════════════════════════════════════════════════════════════
    // ROW MAPPER
    // ══════════════════════════════════════════════════════════════
    
    /**
     * Mappt eine ResultSet-Zeile auf ein Person-Objekt.
     * Zentralisiert, damit Änderungen nur hier nötig sind.
     */
    private Person mapRow(ResultSet rs) throws SQLException {
        return new Person(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getInt("alter"),
            rs.getString("email")
        );
    }
    
    // ══════════════════════════════════════════════════════════════
    // CUSTOM EXCEPTION
    // ══════════════════════════════════════════════════════════════
    
    /**
     * Custom Exception für DAO-Fehler.
     * Unchecked (RuntimeException), damit der aufrufende Code
     * nicht mit try-catch überflutet wird.
     */
    public static class DaoException extends RuntimeException {
        public DaoException(String message) {
            super(message);
        }
        
        public DaoException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}

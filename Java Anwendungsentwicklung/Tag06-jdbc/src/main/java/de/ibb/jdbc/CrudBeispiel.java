package de.ibb.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * CrudBeispiel - Create, Read, Update, Delete mit JDBC.
 * 
 * Tag 6 des Kurses "Java Anwendungsentwicklung"
 * Java Fleet Systems Consulting - java-developer.online
 * 
 * Zeigt alle grundlegenden Datenbankoperationen:
 * - CREATE: INSERT INTO
 * - READ:   SELECT (einzeln und alle)
 * - UPDATE: UPDATE ... SET ... WHERE
 * - DELETE: DELETE FROM ... WHERE
 * 
 * @author Franz-Martin (CTO, Java Fleet Systems Consulting)
 */
public class CrudBeispiel {
    
    private static final String URL = "jdbc:mysql://localhost:3306/ae_tag06_jdbc?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "ae_tag06_jdbc";
    private static final String PASSWORD = "ae_tag06_jdbc";
    
    // ══════════════════════════════════════════════════════════════
    // ENTITY: Person (ein einfaches Datenobjekt)
    // ══════════════════════════════════════════════════════════════
    
    record Person(Integer id, String name, int alter, String email) {
        // Konstruktor für neue Personen (noch keine ID)
        public Person(String name, int alter, String email) {
            this(null, name, alter, email);
        }
        
        @Override
        public String toString() {
            return String.format("Person[id=%d, name=%s, alter=%d, email=%s]",
                id, name, alter, email != null ? email : "null");
        }
    }
    
    // ══════════════════════════════════════════════════════════════
    // MAIN
    // ══════════════════════════════════════════════════════════════
    
    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("  📊 CRUD-Beispiel - Create, Read, Update, Delete");
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println();
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            
            // Tabelle erstellen
            erstelleTabelle(conn);
            
            // ────────────────────────────────────────────────────
            // CREATE - Neue Personen anlegen
            // ────────────────────────────────────────────────────
            System.out.println("📝 CREATE - Personen anlegen:");
            
            Person max = create(conn, new Person("Max Mustermann", 28, "max@example.com"));
            System.out.println("   Erstellt: " + max);
            
            Person lisa = create(conn, new Person("Lisa Schmidt", 34, "lisa@example.com"));
            System.out.println("   Erstellt: " + lisa);
            
            Person tom = create(conn, new Person("Tom Müller", 22, null));
            System.out.println("   Erstellt: " + tom);
            
            System.out.println();
            
            // ────────────────────────────────────────────────────
            // READ - Alle Personen lesen
            // ────────────────────────────────────────────────────
            System.out.println("📖 READ ALL - Alle Personen:");
            List<Person> alle = readAll(conn);
            alle.forEach(p -> System.out.println("   " + p));
            System.out.println();
            
            // ────────────────────────────────────────────────────
            // READ - Eine Person nach ID
            // ────────────────────────────────────────────────────
            System.out.println("🔍 READ BY ID - Person mit ID " + max.id() + ":");
            Optional<Person> gefunden = readById(conn, max.id());
            gefunden.ifPresentOrElse(
                p -> System.out.println("   Gefunden: " + p),
                () -> System.out.println("   Nicht gefunden!")
            );
            System.out.println();
            
            // ────────────────────────────────────────────────────
            // UPDATE - Person aktualisieren
            // ────────────────────────────────────────────────────
            System.out.println("✏️ UPDATE - Max wird älter und ändert E-Mail:");
            Person maxAktualisiert = new Person(max.id(), "Max Mustermann", 29, "max.neu@example.com");
            boolean updated = update(conn, maxAktualisiert);
            System.out.println("   Update erfolgreich: " + updated);
            
            // Prüfen
            readById(conn, max.id()).ifPresent(p -> 
                System.out.println("   Jetzt: " + p));
            System.out.println();
            
            // ────────────────────────────────────────────────────
            // DELETE - Person löschen
            // ────────────────────────────────────────────────────
            System.out.println("🗑️ DELETE - Tom wird gelöscht:");
            boolean deleted = delete(conn, tom.id());
            System.out.println("   Löschen erfolgreich: " + deleted);
            System.out.println();
            
            // ────────────────────────────────────────────────────
            // Finale Übersicht
            // ────────────────────────────────────────────────────
            System.out.println("📋 FINALE ÜBERSICHT:");
            readAll(conn).forEach(p -> System.out.println("   " + p));
            
        } catch (SQLException e) {
            System.err.println("❌ Datenbankfehler: " + e.getMessage());
            e.printStackTrace();
        }
        
        System.out.println();
        System.out.println("═══════════════════════════════════════════════════════════");
    }
    
    // ══════════════════════════════════════════════════════════════
    // CRUD METHODEN
    // ══════════════════════════════════════════════════════════════
    
    /**
     * CREATE - Neue Person anlegen.
     * Gibt die Person MIT generierter ID zurück.
     */
    private static Person create(Connection conn, Person person) throws SQLException {
        String sql = "INSERT INTO personen (name, `alter`, email) VALUES (?, ?, ?)";
        
        // RETURN_GENERATED_KEYS → Wir wollen die generierte ID!
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, person.name());
            ps.setInt(2, person.alter());
            ps.setString(3, person.email());
            
            int affected = ps.executeUpdate();
            
            if (affected > 0) {
                // Generierte ID abrufen
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        int id = keys.getInt(1);
                        return new Person(id, person.name(), person.alter(), person.email());
                    }
                }
            }
        }
        
        throw new SQLException("Person konnte nicht erstellt werden!");
    }
    
    /**
     * READ ALL - Alle Personen lesen.
     */
    private static List<Person> readAll(Connection conn) throws SQLException {
        String sql = "SELECT id, name, `alter`, email FROM personen ORDER BY id";
        List<Person> personen = new ArrayList<>();
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                personen.add(mapRow(rs));
            }
        }
        
        return personen;
    }
    
    /**
     * READ BY ID - Eine Person nach ID suchen.
     * Gibt Optional zurück (leer wenn nicht gefunden).
     */
    private static Optional<Person> readById(Connection conn, int id) throws SQLException {
        String sql = "SELECT id, name, `alter`, email FROM personen WHERE id = ?";
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
            }
        }
        
        return Optional.empty();
    }
    
    /**
     * UPDATE - Person aktualisieren.
     * Gibt true zurück wenn erfolgreich.
     */
    private static boolean update(Connection conn, Person person) throws SQLException {
        String sql = "UPDATE personen SET name = ?, `alter` = ?, email = ? WHERE id = ?";
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, person.name());
            ps.setInt(2, person.alter());
            ps.setString(3, person.email());
            ps.setInt(4, person.id());
            
            int affected = ps.executeUpdate();
            return affected > 0;
        }
    }
    
    /**
     * DELETE - Person löschen.
     * Gibt true zurück wenn erfolgreich.
     */
    private static boolean delete(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM personen WHERE id = ?";
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            
            int affected = ps.executeUpdate();
            return affected > 0;
        }
    }
    
    // ══════════════════════════════════════════════════════════════
    // HELPER
    // ══════════════════════════════════════════════════════════════
    
    /**
     * Erstellt die Tabelle (falls nicht vorhanden).
     */
    private static void erstelleTabelle(Connection conn) throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS personen (
                id INT AUTO_INCREMENT PRIMARY KEY,
                name VARCHAR(100) NOT NULL,
                `alter` INT,
                email VARCHAR(100)
            )
            """;
        
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }
    
    /**
     * Mappt eine ResultSet-Zeile auf ein Person-Objekt.
     * Wiederverwendbar für alle SELECT-Abfragen!
     */
    private static Person mapRow(ResultSet rs) throws SQLException {
        return new Person(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getInt("alter"),
            rs.getString("email")
        );
    }
}

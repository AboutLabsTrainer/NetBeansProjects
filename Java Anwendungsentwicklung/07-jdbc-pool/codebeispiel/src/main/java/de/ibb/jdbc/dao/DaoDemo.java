package de.ibb.jdbc.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * DaoDemo - Das DAO Pattern in Aktion.
 * 
 * Tag 7 des Kurses "Java Anwendungsentwicklung"
 * 
 * Zeigt wie sauber der Code wird, wenn SQL im DAO gekapselt ist.
 * Der Service-Code sieht kein SQL mehr!
 */
public class DaoDemo {
    
    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("  🏗️ DAO Pattern Demo - Saubere Architektur");
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println();
        
        // DataSource erstellen (HikariCP Pool)
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/jae_tag07_jdbc?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC");
        config.setUsername("jae_tag07_jdbc");
        config.setPassword("jae_tag07_jdbc");
        config.setPoolName("DAO-Demo-Pool");
        
        try (HikariDataSource dataSource = new HikariDataSource(config)) {
            
            // Datenbank vorbereiten
            setupDatabase(dataSource);
            
            // DAO erstellen (Dependency Injection!)
            PersonDao personDao = new PersonDaoJdbc(dataSource);
            
            System.out.println("📝 Der Service-Code sieht SO aus (kein SQL!):");
            System.out.println("─────────────────────────────────────────────────────────");
            System.out.println("   PersonDao dao = new PersonDaoJdbc(dataSource);");
            System.out.println("   Person max = dao.save(new Person(\"Max\", 28, \"max@ex.com\"));");
            System.out.println("   Optional<Person> found = dao.findById(1);");
            System.out.println("   List<Person> all = dao.findAll();");
            System.out.println("─────────────────────────────────────────────────────────");
            System.out.println();
            
            // ────────────────────────────────────────────────────────
            // CREATE
            // ────────────────────────────────────────────────────────
            
            System.out.println("📝 CREATE - Personen anlegen:");
            
            Person max = personDao.save(new Person("Max Mustermann", 28, "max@example.com"));
            System.out.println("   ✓ " + max);
            
            Person lisa = personDao.save(new Person("Lisa Schmidt", 34, "lisa@example.com"));
            System.out.println("   ✓ " + lisa);
            
            Person tom = personDao.save(new Person("Tom Müller", 22, null));
            System.out.println("   ✓ " + tom);
            
            System.out.println("   Anzahl: " + personDao.count() + " Personen");
            System.out.println();
            
            // ────────────────────────────────────────────────────────
            // READ
            // ────────────────────────────────────────────────────────
            
            System.out.println("📖 READ - Alle Personen:");
            personDao.findAll().forEach(p -> System.out.println("   " + p));
            System.out.println();
            
            System.out.println("🔍 SEARCH - Suche nach 'Müller':");
            List<Person> gefunden = personDao.findByNameContaining("müller");
            gefunden.forEach(p -> System.out.println("   " + p));
            System.out.println();
            
            // ────────────────────────────────────────────────────────
            // UPDATE
            // ────────────────────────────────────────────────────────
            
            System.out.println("✏️ UPDATE - Max wird älter:");
            Person maxAlt = new Person(max.id(), "Max Mustermann", 29, "max.neu@example.com");
            boolean updated = personDao.update(maxAlt);
            System.out.println("   Update erfolgreich: " + updated);
            
            personDao.findById(max.id()).ifPresent(p -> 
                System.out.println("   Jetzt: " + p));
            System.out.println();
            
            // ────────────────────────────────────────────────────────
            // DELETE
            // ────────────────────────────────────────────────────────
            
            System.out.println("🗑️ DELETE - Tom wird gelöscht:");
            boolean deleted = personDao.deleteById(tom.id());
            System.out.println("   Löschen erfolgreich: " + deleted);
            System.out.println("   Anzahl jetzt: " + personDao.count() + " Personen");
            System.out.println();
            
            // ────────────────────────────────────────────────────────
            // Finale Übersicht
            // ────────────────────────────────────────────────────────
            
            System.out.println("📋 FINALE ÜBERSICHT:");
            personDao.findAll().forEach(p -> System.out.println("   " + p));
            
        } catch (Exception e) {
            System.err.println("❌ Fehler: " + e.getMessage());
            e.printStackTrace();
        }
        
        System.out.println();
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("  💡 Vorteile des DAO Patterns:");
        System.out.println("     • SQL ist NUR im DAO (nicht überall verstreut)");
        System.out.println("     • Austauschbar: JDBC → JPA → MongoDB");
        System.out.println("     • Testbar: Mock-DAO für Unit Tests");
        System.out.println("═══════════════════════════════════════════════════════════");
    }
    
    private static void setupDatabase(HikariDataSource dataSource) throws SQLException {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            
            stmt.execute("""
                CREATE TABLE personen (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(100) NOT NULL,
                    alter INT,
                    email VARCHAR(100)
                )
                """);
        }
    }
}

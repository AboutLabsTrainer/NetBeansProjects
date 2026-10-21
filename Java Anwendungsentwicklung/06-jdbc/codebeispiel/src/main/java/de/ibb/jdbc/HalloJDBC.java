package de.ibb.jdbc;

import java.sql.*;

/**
 * HalloJDBC - Deine erste Datenbankverbindung mit JDBC.
 * 
 * Tag 6 des Kurses "Java Anwendungsentwicklung"
 * 
 * Die Übung spricht MySQL in Docker an.
 * Datenbank ibb-db, Benutzer ibb-user, Passwort ibb-pw.
 */
public class HalloJDBC {
    
    // ══════════════════════════════════════════════════════════════
    // JDBC URL - Der "Wegweiser" zur Datenbank
    // ══════════════════════════════════════════════════════════════
    
    // Format: jdbc:<datenbank>:<verbindungsdetails>
    // MySQL in Docker: Datenbank ibb-db, Benutzer ibb-user, Passwort ibb-pw
    private static final String URL = "jdbc:mysql://localhost:3306/ibb-db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "ibb-user";
    private static final String PASSWORD = "ibb-pw";
    
    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("  🚀 HALLO JDBC - Deine erste Datenbankverbindung");
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println();
        
        // ──────────────────────────────────────────────────────────
        // SCHRITT 1: Verbindung herstellen
        // ──────────────────────────────────────────────────────────
        
        // try-with-resources: Connection wird AUTOMATISCH geschlossen!
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            
            System.out.println("✅ Verbindung hergestellt!");
            System.out.println("   URL: " + URL);
            System.out.println("   Datenbank: " + conn.getMetaData().getDatabaseProductName());
            System.out.println("   Version: " + conn.getMetaData().getDatabaseProductVersion());
            System.out.println();
            
            // ──────────────────────────────────────────────────────────
            // SCHRITT 2: Tabelle erstellen (DDL)
            // ──────────────────────────────────────────────────────────
            
            String createTableSQL = """
                CREATE TABLE jae06_personen (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(100) NOT NULL,
                    `alter` INT,
                    email VARCHAR(100)
                )
                """;
            
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(createTableSQL);
                System.out.println("✅ Tabelle 'jae06_personen' erstellt!");
            }
            
            // ──────────────────────────────────────────────────────────
            // SCHRITT 3: Daten einfügen (DML mit PreparedStatement)
            // ──────────────────────────────────────────────────────────
            
            String insertSQL = "INSERT INTO jae06_personen (name, `alter`, email) VALUES (?, ?, ?)";
            
            try (PreparedStatement ps = conn.prepareStatement(insertSQL)) {
                // Erste Person
                ps.setString(1, "Max Mustermann");
                ps.setInt(2, 28);
                ps.setString(3, "max@example.com");
                ps.executeUpdate();
                
                // Zweite Person
                ps.setString(1, "Lisa Schmidt");
                ps.setInt(2, 34);
                ps.setString(3, "lisa@example.com");
                ps.executeUpdate();
                
                // Dritte Person
                ps.setString(1, "Tom Müller");
                ps.setInt(2, 22);
                ps.setString(3, null);  // E-Mail kann null sein
                ps.executeUpdate();
                
                System.out.println("✅ 3 Personen eingefügt!");
                System.out.println();
            }
            
            // ──────────────────────────────────────────────────────────
            // SCHRITT 4: Daten abfragen (SELECT)
            // ──────────────────────────────────────────────────────────
            
            String selectSQL = "SELECT id, name, `alter`, email FROM jae06_personen";
            
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(selectSQL)) {
                
                System.out.println("📋 Alle Personen in der Datenbank:");
                System.out.println("─────────────────────────────────────────────────");
                System.out.printf("%-4s %-20s %-6s %-25s%n", "ID", "Name", "Alter", "E-Mail");
                System.out.println("─────────────────────────────────────────────────");
                
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int alter = rs.getInt("alter");
                    String email = rs.getString("email");
                    
                    // Null-Check für optionale Felder
                    if (rs.wasNull()) {
                        // Letzter gelesener Wert war NULL
                    }
                    
                    System.out.printf("%-4d %-20s %-6d %-25s%n", 
                        id, 
                        name, 
                        alter, 
                        email != null ? email : "(keine)");
                }
                System.out.println("─────────────────────────────────────────────────");
            }
            
            System.out.println();
            System.out.println("✅ Fertig! Die Connection wird jetzt automatisch geschlossen.");
            
        } catch (SQLException e) {
            System.err.println("❌ Datenbankfehler: " + e.getMessage());
            e.printStackTrace();
        }
        
        System.out.println();
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("  💡 Hinweis: Die Zeilen bleiben in MySQL (ibb-db), bis das Programm sie loescht.");
        System.out.println("═══════════════════════════════════════════════════════════");
    }
}

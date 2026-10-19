package de.ibb.jdbc;

import java.sql.*;

/**
 * SqlInjectionDemo - Warum PreparedStatement PFLICHT ist!
 * 
 * Tag 6 des Kurses "Java Anwendungsentwicklung"
 * Java Fleet Systems Consulting - java-developer.online
 * 
 * ⚠️ WARNUNG: Dieser Code zeigt ABSICHTLICH unsichere Praktiken!
 * NIEMALS String-Konkatenation für SQL in Produktion verwenden!
 * 
 * @author Franz-Martin (CTO, Java Fleet Systems Consulting)
 */
public class SqlInjectionDemo {
    
    private static final String URL = "jdbc:mysql://localhost:3306/jae_tag06_jdbc?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "jae_tag06_jdbc";
    private static final String PASSWORD = "jae_tag06_jdbc";
    
    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("  ⚠️ SQL INJECTION DEMO - Warum PreparedStatement PFLICHT ist");
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println();
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            
            setupDatabase(conn);
            
            // ════════════════════════════════════════════════════════
            // SZENARIO 1: Normale Login-Abfrage
            // ════════════════════════════════════════════════════════
            
            System.out.println("🔐 SZENARIO 1: Normaler Login-Versuch");
            System.out.println("─────────────────────────────────────────────────────────");
            
            String normalUser = "admin";
            String normalPass = "geheim123";
            
            System.out.println("   Eingabe: User='" + normalUser + "', Pass='" + normalPass + "'");
            
            // UNSICHER (nur zur Demonstration!)
            boolean unsafeResult = loginUnsafe(conn, normalUser, normalPass);
            System.out.println("   ❌ Unsichere Methode: " + (unsafeResult ? "Eingeloggt" : "Abgelehnt"));
            
            // SICHER
            boolean safeResult = loginSafe(conn, normalUser, normalPass);
            System.out.println("   ✅ Sichere Methode:   " + (safeResult ? "Eingeloggt" : "Abgelehnt"));
            
            System.out.println();
            
            // ════════════════════════════════════════════════════════
            // SZENARIO 2: SQL Injection Angriff!
            // ════════════════════════════════════════════════════════
            
            System.out.println("💀 SZENARIO 2: SQL Injection Angriff!");
            System.out.println("─────────────────────────────────────────────────────────");
            
            // Der Angreifer gibt das als "Username" ein:
            String evilUser = "' OR '1'='1' --";
            String evilPass = "egal";
            
            System.out.println("   Eingabe: User='" + evilUser + "', Pass='" + evilPass + "'");
            System.out.println();
            
            // UNSICHER - WIRD GEHACKT!
            System.out.println("   ❌ UNSICHERE Methode:");
            boolean unsafeHacked = loginUnsafe(conn, evilUser, evilPass);
            System.out.println("      Ergebnis: " + (unsafeHacked ? "💀 EINGELOGGT (GEHACKT!)" : "Abgelehnt"));
            
            System.out.println();
            
            // SICHER - Angriff schlägt fehl
            System.out.println("   ✅ SICHERE Methode:");
            boolean safeProtected = loginSafe(conn, evilUser, evilPass);
            System.out.println("      Ergebnis: " + (safeProtected ? "Eingeloggt" : "🛡️ ABGELEHNT (Geschützt!)"));
            
            System.out.println();
            
            // ════════════════════════════════════════════════════════
            // ERKLÄRUNG
            // ════════════════════════════════════════════════════════
            
            System.out.println("📚 WAS IST PASSIERT?");
            System.out.println("─────────────────────────────────────────────────────────");
            System.out.println();
            System.out.println("❌ UNSICHERE Abfrage (String-Konkatenation):");
            System.out.println("   SELECT * FROM users WHERE name='' OR '1'='1' --' AND pass='egal'");
            System.out.println("                              ^^^^^^^^^^^^^^^^^^^");
            System.out.println("   '1'='1' ist IMMER wahr! -- kommentiert den Rest aus.");
            System.out.println("   → Gibt den ERSTEN User zurück = Login als Admin!");
            System.out.println();
            System.out.println("✅ SICHERE Abfrage (PreparedStatement):");
            System.out.println("   SELECT * FROM users WHERE name=? AND pass=?");
            System.out.println("   Parameter: [\"' OR '1'='1' --\", \"egal\"]");
            System.out.println("   → Sucht nach einem User mit dem Namen \"' OR '1'='1' --\"");
            System.out.println("   → Findet nichts = Login fehlgeschlagen!");
            
            System.out.println();
            System.out.println("═══════════════════════════════════════════════════════════");
            System.out.println("  🛡️ FAZIT: IMMER PreparedStatement verwenden!");
            System.out.println("═══════════════════════════════════════════════════════════");
            
        } catch (SQLException e) {
            System.err.println("❌ Fehler: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // ══════════════════════════════════════════════════════════════
    // UNSICHERE LOGIN-METHODE (NUR ZUR DEMONSTRATION!)
    // ══════════════════════════════════════════════════════════════
    
    /**
     * ⚠️ NIEMALS SO MACHEN! Nur zur Demonstration der Schwachstelle.
     */
    @SuppressWarnings("SqlInjection") // IDE-Warnung unterdrücken (absichtlich unsicher)
    private static boolean loginUnsafe(Connection conn, String username, String password) 
            throws SQLException {
        
        // ❌ FALSCH: String-Konkatenation = SQL Injection möglich!
        String sql = "SELECT * FROM users WHERE name='" + username + "' AND pass='" + password + "'";
        
        System.out.println("      SQL: " + sql);
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            return rs.next(); // true wenn mindestens ein Ergebnis
        }
    }
    
    // ══════════════════════════════════════════════════════════════
    // SICHERE LOGIN-METHODE
    // ══════════════════════════════════════════════════════════════
    
    /**
     * ✅ SO MACHT MAN ES RICHTIG: PreparedStatement mit Parametern.
     */
    private static boolean loginSafe(Connection conn, String username, String password) 
            throws SQLException {
        
        // ✅ RICHTIG: Prepared Statement mit Platzhaltern
        String sql = "SELECT * FROM users WHERE name = ? AND pass = ?";
        
        System.out.println("      SQL: " + sql);
        System.out.println("      Params: [\"" + username + "\", \"" + password + "\"]");
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }
    
    // ══════════════════════════════════════════════════════════════
    // SETUP
    // ══════════════════════════════════════════════════════════════
    
    private static void setupDatabase(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            // Tabelle erstellen
            stmt.execute("""
                CREATE TABLE users (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(100) NOT NULL,
                    pass VARCHAR(100) NOT NULL,
                    role VARCHAR(50)
                )
                """);
            
            // Testdaten (in Produktion: Passwörter HASHEN!)
            stmt.execute("INSERT INTO users (name, pass, role) VALUES ('admin', 'geheim123', 'ADMIN')");
            stmt.execute("INSERT INTO users (name, pass, role) VALUES ('user1', 'pass1', 'USER')");
            stmt.execute("INSERT INTO users (name, pass, role) VALUES ('user2', 'pass2', 'USER')");
        }
        
        System.out.println("📦 Datenbank vorbereitet: 3 User (admin, user1, user2)");
        System.out.println();
    }
}

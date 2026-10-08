package de.ibb.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;

/**
 * TransactionDemo - Alles oder Nichts mit Transaktionen.
 * 
 * Tag 7 des Kurses "Java Anwendungsentwicklung"
 * Java Fleet Systems Consulting - java-developer.online
 * 
 * Zeigt:
 * - autoCommit deaktivieren
 * - commit() bei Erfolg
 * - rollback() bei Fehler
 * - Das klassische Banküberweisung-Beispiel
 * 
 * @author Franz-Martin (CTO, Java Fleet Systems Consulting)
 */
public class TransactionDemo {
    
    private static HikariDataSource dataSource;
    
    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("  💳 Transaction Demo - Alles oder Nichts");
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println();
        
        // DataSource initialisieren
        setupDataSource();
        
        try {
            // Datenbank vorbereiten
            setupDatabase();
            
            // Kontostände anzeigen
            System.out.println("📊 AUSGANGSZUSTAND:");
            zeigeKontostaende();
            System.out.println();
            
            // ────────────────────────────────────────────────────────
            // SZENARIO 1: Erfolgreiche Überweisung
            // ────────────────────────────────────────────────────────
            
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("✅ SZENARIO 1: Erfolgreiche Überweisung (100€ A → B)");
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            
            ueberweisen("A", "B", 100, false);  // kein Fehler
            
            System.out.println("\n📊 Nach erfolgreicher Überweisung:");
            zeigeKontostaende();
            System.out.println();
            
            // ────────────────────────────────────────────────────────
            // SZENARIO 2: Überweisung mit Fehler → Rollback!
            // ────────────────────────────────────────────────────────
            
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("❌ SZENARIO 2: Überweisung mit Fehler (200€ B → A)");
            System.out.println("   → Simulierter Crash zwischen den UPDATEs!");
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            
            ueberweisen("B", "A", 200, true);  // MIT Fehler!
            
            System.out.println("\n📊 Nach fehlgeschlagener Überweisung (Rollback!):");
            zeigeKontostaende();
            System.out.println();
            
            System.out.println("🎉 Beachte: Die Kontostände sind UNVERÄNDERT!");
            System.out.println("   Transaction hat den Rollback korrekt durchgeführt.");
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (dataSource != null) {
                dataSource.close();
            }
        }
        
        System.out.println();
        System.out.println("═══════════════════════════════════════════════════════════");
    }
    
    /**
     * Überweist Geld von einem Konto auf ein anderes.
     * 
     * @param von Quellkonto
     * @param nach Zielkonto
     * @param betrag Betrag in Euro
     * @param simuliereFehler Wenn true, wird zwischen den UPDATEs ein Fehler geworfen
     */
    private static void ueberweisen(String von, String nach, double betrag, 
                                    boolean simuliereFehler) throws SQLException {
        
        Connection conn = null;
        
        try {
            conn = dataSource.getConnection();
            
            // ⚠️ WICHTIG: AutoCommit deaktivieren!
            // Sonst wird jedes Statement sofort committed.
            conn.setAutoCommit(false);
            
            System.out.println("   🔄 AutoCommit deaktiviert");
            System.out.println("   📤 Überweise " + betrag + "€ von Konto " + von + " nach " + nach);
            
            // Schritt 1: Geld abbuchen
            String abbuchenSQL = "UPDATE konten SET stand = stand - ? WHERE konto_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(abbuchenSQL)) {
                ps.setDouble(1, betrag);
                ps.setString(2, von);
                int rows = ps.executeUpdate();
                System.out.println("   ✓ Schritt 1: " + rows + " Zeile(n) - Abgebucht von " + von);
            }
            
            // Simulierter Crash!
            if (simuliereFehler) {
                System.out.println("   💥 CRASH! Server stirbt hier...");
                throw new RuntimeException("Simulierter Serverabsturz!");
            }
            
            // Schritt 2: Geld gutschreiben
            String gutschreibenSQL = "UPDATE konten SET stand = stand + ? WHERE konto_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(gutschreibenSQL)) {
                ps.setDouble(1, betrag);
                ps.setString(2, nach);
                int rows = ps.executeUpdate();
                System.out.println("   ✓ Schritt 2: " + rows + " Zeile(n) - Gutgeschrieben auf " + nach);
            }
            
            // Alles okay → COMMIT!
            conn.commit();
            System.out.println("   ✅ COMMIT - Transaktion erfolgreich!");
            
        } catch (Exception e) {
            // Fehler → ROLLBACK!
            System.out.println("   ❌ FEHLER: " + e.getMessage());
            
            if (conn != null) {
                try {
                    conn.rollback();
                    System.out.println("   🔙 ROLLBACK - Alle Änderungen rückgängig gemacht!");
                } catch (SQLException rollbackEx) {
                    System.err.println("Rollback fehlgeschlagen: " + rollbackEx.getMessage());
                }
            }
            
        } finally {
            // Connection zurückgeben (AutoCommit wieder aktivieren!)
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);  // Wichtig für den Pool!
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    private static void zeigeKontostaende() throws SQLException {
        String sql = "SELECT konto_id, inhaber, stand FROM konten ORDER BY konto_id";
        
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            System.out.println("   ┌────────────┬─────────────────┬────────────┐");
            System.out.println("   │ Konto      │ Inhaber         │ Stand      │");
            System.out.println("   ├────────────┼─────────────────┼────────────┤");
            
            while (rs.next()) {
                System.out.printf("   │ %-10s │ %-15s │ %,10.2f€│%n",
                    rs.getString("konto_id"),
                    rs.getString("inhaber"),
                    rs.getDouble("stand"));
            }
            
            System.out.println("   └────────────┴─────────────────┴────────────┘");
        }
    }
    
    private static void setupDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/ae_tag07_jdbc?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC");
        config.setUsername("ae_tag07_jdbc");
        config.setPassword("ae_tag07_jdbc");
        config.setPoolName("Transaction-Demo-Pool");
        dataSource = new HikariDataSource(config);
    }
    
    private static void setupDatabase() throws SQLException {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            
            stmt.execute("""
                CREATE TABLE konten (
                    konto_id VARCHAR(10) PRIMARY KEY,
                    inhaber VARCHAR(100) NOT NULL,
                    stand DECIMAL(10,2) NOT NULL
                )
                """);
            
            stmt.execute("INSERT INTO konten VALUES ('A', 'Max Mustermann', 1000.00)");
            stmt.execute("INSERT INTO konten VALUES ('B', 'Lisa Schmidt', 500.00)");
        }
    }
}

package de.ibb.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;

/**
 * ConnectionPoolDemo - Warum Connection Pooling PFLICHT ist.
 * 
 * Tag 7 des Kurses "Java Anwendungsentwicklung"
 * Java Fleet Systems Consulting - java-developer.online
 * 
 * HikariCP ist der schnellste und zuverlässigste Connection Pool.
 * Spring Boot verwendet ihn standardmäßig!
 * 
 * @author Franz-Martin (CTO, Java Fleet Systems Consulting)
 */
public class ConnectionPoolDemo {
    
    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("  🏊 Connection Pool Demo - HikariCP");
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println();
        
        // ══════════════════════════════════════════════════════════════
        // VERGLEICH: Ohne Pool vs. Mit Pool
        // ══════════════════════════════════════════════════════════════
        
        String url = "jdbc:mysql://localhost:3306/ae_tag07_jdbc?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String user = "ae_tag07_jdbc";
        String password = "ae_tag07_jdbc";
        
        int anzahlOperationen = 100;
        
        // ────────────────────────────────────────────────────────
        // OHNE POOL - Jedes Mal neue Connection
        // ────────────────────────────────────────────────────────
        
        System.out.println("❌ OHNE Connection Pool:");
        System.out.println("   " + anzahlOperationen + " Operationen, jedes Mal neue Connection...");
        
        long startOhne = System.currentTimeMillis();
        
        for (int i = 0; i < anzahlOperationen; i++) {
            try (Connection conn = DriverManager.getConnection(url, user, password)) {
                // Einfache Operation
                try (Statement stmt = conn.createStatement()) {
                    stmt.execute("SELECT 1");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        long dauerOhne = System.currentTimeMillis() - startOhne;
        System.out.println("   ⏱️ Dauer: " + dauerOhne + " ms");
        System.out.println();
        
        // ────────────────────────────────────────────────────────
        // MIT POOL - Connections werden wiederverwendet
        // ────────────────────────────────────────────────────────
        
        System.out.println("✅ MIT HikariCP Connection Pool:");
        System.out.println("   " + anzahlOperationen + " Operationen, Connections aus Pool...");
        
        // HikariCP konfigurieren
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(user);
        config.setPassword(password);
        
        // Pool-Konfiguration
        config.setMaximumPoolSize(10);          // Max 10 Connections
        config.setMinimumIdle(2);                // Min 2 Connections bereit
        config.setConnectionTimeout(30000);      // 30s Timeout
        config.setIdleTimeout(600000);           // 10min bis idle Connection geschlossen
        config.setMaxLifetime(1800000);          // 30min max Lebenszeit
        
        // Pool-Name für Logging
        config.setPoolName("de.ibb-pool");
        
        // DataSource erstellen (macht der Pool einmal beim Start!)
        try (HikariDataSource dataSource = new HikariDataSource(config)) {
            
            long startMit = System.currentTimeMillis();
            
            for (int i = 0; i < anzahlOperationen; i++) {
                // Connection aus Pool holen (SCHNELL!)
                try (Connection conn = dataSource.getConnection()) {
                    try (Statement stmt = conn.createStatement()) {
                        stmt.execute("SELECT 1");
                    }
                }
                // conn.close() gibt die Connection zurück in den Pool (nicht wirklich schließen!)
            }
            
            long dauerMit = System.currentTimeMillis() - startMit;
            System.out.println("   ⏱️ Dauer: " + dauerMit + " ms");
            System.out.println();
            
            // ────────────────────────────────────────────────────────
            // ERGEBNIS
            // ────────────────────────────────────────────────────────
            
            double speedup = (double) dauerOhne / dauerMit;
            
            System.out.println("📊 ERGEBNIS:");
            System.out.println("─────────────────────────────────────────────────────────");
            System.out.println("   Ohne Pool: " + dauerOhne + " ms");
            System.out.println("   Mit Pool:  " + dauerMit + " ms");
            System.out.printf("   Speedup:   %.1fx schneller!%n", speedup);
            System.out.println("─────────────────────────────────────────────────────────");
            System.out.println();
            
            // Pool-Statistiken
            System.out.println("📈 Pool-Statistiken:");
            System.out.println("   Aktive Connections: " + dataSource.getHikariPoolMXBean().getActiveConnections());
            System.out.println("   Idle Connections:   " + dataSource.getHikariPoolMXBean().getIdleConnections());
            System.out.println("   Total Connections:  " + dataSource.getHikariPoolMXBean().getTotalConnections());
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        System.out.println();
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("  💡 In Produktion: Connection Pool ist PFLICHT!");
        System.out.println("     Spring Boot verwendet HikariCP automatisch.");
        System.out.println("═══════════════════════════════════════════════════════════");
    }
}

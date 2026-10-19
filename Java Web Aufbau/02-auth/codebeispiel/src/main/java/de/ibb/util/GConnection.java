package de.ibb.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import jakarta.servlet.ServletContext;

/**
 * Liest db.url, db.user und db.password aus dem ServletContext und öffnet die JDBC-Verbindung. Der Aufrufer schließt sie.
 */
public class GConnection {

    private final String url;
    private final String user;
    private final String password;

    public GConnection(ServletContext ctx) {
        this.url = ctx.getInitParameter("db.url");
        this.user = ctx.getInitParameter("db.user");
        this.password = ctx.getInitParameter("db.password");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("MySQL JDBC Driver not found. Add mysql-connector-j to classpath.", e);
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}

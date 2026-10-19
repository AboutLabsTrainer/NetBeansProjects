package jakarta.sql;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Minimal stub for compilation when jakarta.jakartaee-api does not include this package.
 * At runtime Payara provides the real implementation.
 */
public interface DataSource {
    Connection getConnection() throws SQLException;
    Connection getConnection(String username, String password) throws SQLException;
}

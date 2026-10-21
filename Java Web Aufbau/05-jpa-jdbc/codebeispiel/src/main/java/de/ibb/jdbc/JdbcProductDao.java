package de.ibb.jdbc;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Liest Produktnamen über die injizierte DataSource jdbc/ibb. Das ist der JDBC-Weg neben dem EntityManager.
 */
@RequestScoped
public class JdbcProductDao {

    @Resource(lookup = "jdbc/ibb")
    private DataSource ds;

    public List<String> findAllProductNames() throws SQLException {
        try (Connection con = ds.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT name FROM jwebak05_products ORDER BY id");
             ResultSet rs = ps.executeQuery()) {
            List<String> names = new ArrayList<>();
            while (rs.next()) names.add(rs.getString(1));
            return names;
        }
    }
}

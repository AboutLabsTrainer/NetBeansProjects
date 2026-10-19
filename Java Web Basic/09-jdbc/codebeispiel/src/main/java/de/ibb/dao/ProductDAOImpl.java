package de.ibb.dao;

import de.ibb.model.Product;
import jakarta.annotation.Resource;
import jakarta.naming.InitialContext;
import jakarta.naming.NamingException;
import jakarta.sql.DataSource;

import java.sql.*;
import java.util.List;
import java.util.Optional;

/**
 * Sucht die DataSource jdbc/jwebb_tag10_jdbc per Injection oder JNDI und führt die JDBC-Zugriffe aus.
 */

public class ProductDAOImpl implements ProductDAO {

    @Resource(lookup = "jdbc/jwebb_tag10_jdbc")
    private DataSource injected;

    private DataSource dataSource;

    public ProductDAOImpl() {
        this.dataSource = lookup();
    }

    private DataSource lookup() {
        if (injected != null) {
            return injected;
        }
        try {
            InitialContext ctx = new InitialContext();
            return (DataSource) ctx.lookup("java:comp/env/jdbc/jwebb_tag10_jdbc");
        } catch (NamingException ex) {
            try {
                InitialContext ctx = new InitialContext();
                return (DataSource) ctx.lookup("jdbc/jwebb_tag10_jdbc");
            } catch (NamingException e) {
                throw new RuntimeException("Could not find DataSource 'jdbc/jwebb_tag10_jdbc' in JNDI. Configure Payara resource.", e);
            }
        }
    }

    @Override
    public Optional<Product> findById(int id) throws Exception {
        String sql = "SELECT id, name, price, stock FROM jwebb_products WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Product(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("price"),
                            rs.getInt("stock")
                    ));
                }
                return Optional.empty();
            }
        }
    }

    @Override
    public void insertSingle(Product p) throws Exception {
        String sql = "INSERT INTO jwebb_products(name, price, stock) VALUES(?,?,?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getName());
            ps.setDouble(2, p.getPrice());
            ps.setInt(3, p.getStock());
            ps.executeUpdate();
        }
    }

    @Override
    public void insertBatch(List<Product> items) throws Exception {
        String sql = "INSERT INTO jwebb_products(name, price, stock) VALUES(?,?,?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            for (Product p : items) {
                ps.setString(1, p.getName());
                ps.setDouble(2, p.getPrice());
                ps.setInt(3, p.getStock());
                ps.addBatch();
            }
            ps.executeBatch();
            conn.commit();
        }
    }

    @Override
    public void transactionCommitAndRollback() throws Exception {
        String sqlOk = "INSERT INTO jwebb_products(name, price, stock) VALUES(?,?,?)";
        String sqlFail = "INSERT INTO jwebb_products(id, name, price, stock) VALUES(1,?,?,?)"; // provoke PK clash
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ok = conn.prepareStatement(sqlOk);
             PreparedStatement fail = conn.prepareStatement(sqlFail)) {
            conn.setAutoCommit(false);

            ok.setString(1, "OK TX");
            ok.setDouble(2, 1.23);
            ok.setInt(3, 1);
            ok.executeUpdate();

            fail.setString(1, "FAIL TX");
            fail.setDouble(2, 9.99);
            fail.setInt(3, 1);

            try {
                fail.executeUpdate();
                conn.commit();
            } catch (SQLException ex) {
                conn.rollback();
            } finally {
                conn.setAutoCommit(true);
            }
        }
    }
}

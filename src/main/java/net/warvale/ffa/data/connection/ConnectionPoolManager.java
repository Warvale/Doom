package net.warvale.ffa.data.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.Bukkit;
import net.warvale.ffa.WarvaleFFA;
import net.warvale.ffa.config.DatabaseCredentials;

import java.sql.*;
import java.util.logging.Level;


public class ConnectionPoolManager {

    private HikariDataSource dataSource;
    private DatabaseCredentials credentials;
    private int minimumConnections;
    private int maximumConnections;
    private long connectionTimeout;

    public ConnectionPoolManager(DatabaseCredentials credentials) {
        this.credentials = credentials;

        this.init();
        this.setupPool();
    }

    private void init() {
        this.minimumConnections = 0;
        this.maximumConnections = 25;
        this.connectionTimeout = 10000L;
    }

    private void setupPool() {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl("jdbc:mysql://" + this.credentials.getHostname() + ":" + this.credentials.getPort() + "/" + this.credentials.getDatabaseName());
        config.setDriverClassName("com.mysql.jdbc.Driver");
        config.setUsername(this.credentials.getUsername());
        config.setPassword(this.credentials.getPassword());
        config.setMinimumIdle(this.minimumConnections);
        config.setMaximumPoolSize(this.maximumConnections);
        config.setConnectionTimeout(this.connectionTimeout);

        try {
            this.dataSource = new HikariDataSource(config);
        }
        catch (Exception e) {
            WarvaleFFA.get().getLogger().log(Level.SEVERE, "Unable to establish MySQL connection, plugin disabled.");
            Bukkit.getServer().getPluginManager().disablePlugin(WarvaleFFA.get());
        }
    }

    public Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }

    public void close(Connection conn, Statement statement, ResultSet res) {
        if (conn != null) {
            try {
                conn.close();
            }
            catch (SQLException ex) {}
        }

        if (statement != null) {
            try {
                statement.close();
            }
            catch (SQLException ex) {}
        }

        if (res != null) {
            try {
                res.close();
            }
            catch (SQLException ex) {}
        }
    }

    public void close(Connection conn, PreparedStatement ps, ResultSet res) {
        if (conn != null) {
            try {
                conn.close();
            }
            catch (SQLException ex) {}
        }

        if (ps != null) {
            try {
                ps.close();
            }
            catch (SQLException ex) {}
        }

        if (res != null) {
            try {
                res.close();
            }
            catch (SQLException ex) {}
        }
    }

    public void closePool() {
        if (this.dataSource != null && !this.dataSource.isClosed()) {
            this.dataSource.close();
        }
    }

}

package org.example;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class HikariDatabasePoolConnection {

    private static final HikariConfig config = new HikariConfig();
    private static final HikariDataSource hikariPool;

    public static String username = null;
    public static String password = null;

    public static String URL = null;

    static {

        //TODO: change password back

        String password = "gigolo69";
        String username = "root";
        String URL = "jdbc:mysql://localhost:3306/laboratory8";
        config.setJdbcUrl(URL);
        config.setUsername(username);
        config.setPassword(password);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.setConnectionTimeout(300000);
        config.setIdleTimeout(120000);
        hikariPool = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return hikariPool.getConnection();
    }

    private HikariDatabasePoolConnection() {};

}

package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static Database database = null;

    private static Connection connection = null;

    private Database() {

    }

    public static synchronized Database getInstance() {
        if(database == null)
            database = new Database();
        return database;
    }


    public static Connection getConnection() throws SQLException, ClassNotFoundException {
         return HikariDatabasePoolConnection.getConnection();
    }
}

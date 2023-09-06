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

    private static void createConnection() throws ClassNotFoundException, SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            connection = DriverManager.getConnection(HikariDatabasePoolConnection.URL,
                                                     HikariDatabasePoolConnection.username,
                                                     HikariDatabasePoolConnection.password);
        }
        catch(SQLException e) {
            System.out.println("The database doesn't exist or the logins are incorrect");
        }

    }


    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if (connection == null)
            createConnection();
        return HikariDatabasePoolConnection.getConnection();
    }
}

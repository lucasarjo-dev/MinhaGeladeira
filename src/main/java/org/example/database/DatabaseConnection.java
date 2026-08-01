package org.example.database;
import java.sql.*;
public class DatabaseConnection {
private static final String url = "jdbc:mysql://127.0.0.1:3306/myfridge?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
private static final String user = "root";
private static final String password = "Nightreign2";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}


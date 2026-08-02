package org.example.database;
import java.sql.*;
public class DatabaseConnection {
private static final String url = "jdbc:mysql://127.0.0.1:3306/MinhaGeladeira?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
private static final String user = "";
private static final String password = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}


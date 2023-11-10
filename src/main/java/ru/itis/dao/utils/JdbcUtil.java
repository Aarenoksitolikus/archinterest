package ru.itis.dao.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnectionUtil {
    private static Connection conn = null;
    private static final String url = "jdbc:postgresql://localhost:5432/archinterest_db";
    private static final String username = "postgres";
    private static final String password = "qwerty007";

    public static Connection getConnection() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }
}

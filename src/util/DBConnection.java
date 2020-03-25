package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/students?useSSL=false";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "123456789";

    public Connection checkUser() {
        try {
            //Loading drivers for MySQL
            Class.forName("com.mysql.jdbc.Driver");

            //Creating connection with the database

            return DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

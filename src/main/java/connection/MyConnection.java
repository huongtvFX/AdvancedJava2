package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    public static final String DB_USER = "root";
    public static final String DB_PASS = "";
    public static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3307/bkacad";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_CONNECTION_URL, DB_USER, DB_PASS);
    }
}


package model;
import java.sql.*;

public class DBConnect {

    static final String DB_URL = "jdbc:postgresql://localhost/BRCM";
    // Database Credentials
    static final String USER = "postgres"; // Default user name should be "postgres" unless you changed it.
    static final String PASS = System.getenv("POSTGRE_PASSWORD");
    public static Connection conn = startConnection();
    
    public static Connection startConnection(){
        try {
            // DB Connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connection Successful!");
            return conn;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return null;
    }
}

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;


public class Main extends Application {

    // Database URL
    static final String DB_URL = "jdbc:postgresql://localhost/BRCM";
    // Database Credentials
    static final String USER = "postgres"; // Default user name should be "postgres" unless you changed it.
    static final String PASS = System.getenv("POSTGRE_PASSWORD"); // Replace "System.getenv()" with a String containing
                                                                  // your database password.

    public static void main(String[] args) {
        try {
            // DB Connection
            System.out.println("Connecting to database...");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connection Successful!");
            // Launch GUI
            launch(args);
            conn.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/LoginView.fxml"));
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Bronco Recreational Complex Management");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

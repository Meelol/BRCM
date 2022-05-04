import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;


public class Main extends Application {

    public static void main(String[] args) {
            // Launch GUI
            launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/LoginView.fxml"));
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Bronco Recreational Complex Management");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static Connection shareConn(Connection conn){
        return conn;
    }
}

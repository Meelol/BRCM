package control;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.ManagementModel;

public class ManagementController {

    @FXML
    private Button logOutButton;
    @FXML
    private Button reportButton;
    
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void printEarningsReport() throws SQLException{
        ResultSet rs = ManagementModel.getOrders();
        int counter = 0;
        float totalEarnings = 0;
        while(rs.next()){
            counter++;
            totalEarnings += rs.getFloat("totalPrice");
        }
        System.out.println("\n\n\nAverage Earnings Per Order: " + (totalEarnings / counter) + "\nTotal Earnings: " + totalEarnings);
    }

    // Switch to login screen when log out is clicked
    public void switchToLoginScene(ActionEvent event) throws IOException {
        System.out.println("Logging Out!");
        this.root = FXMLLoader.load(getClass().getResource("../view/LoginView.fxml"));
        this.stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        this.scene = new Scene(this.root);
        stage.setScene(scene);
        stage.show();
        }
}

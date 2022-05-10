package control;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class ShoppingCartController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button goBackButton;
    @FXML
    private Button logOutButton;

        // Switch to login screen when log out is clicked
        public void switchToLoginScene(ActionEvent event) throws IOException {
            System.out.println("Logging Out!");
            this.root = FXMLLoader.load(getClass().getResource("../view/LoginView.fxml"));
            this.stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
            this.scene = new Scene(this.root);
            stage.setScene(scene);
            stage.show();
        }
    
        // Switch to MainMenuView when backArrow is clicked
        public void switchToProductsScene(ActionEvent event) throws IOException {
            System.out.println("Back to Products!");
            this.root = FXMLLoader.load(getClass().getResource("../view/ProductsView.fxml"));
            this.stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
            this.scene = new Scene(this.root);
            stage.setScene(scene);
            stage.show();
        }
}

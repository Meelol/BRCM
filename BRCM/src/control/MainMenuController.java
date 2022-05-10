package control;

import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import model.MainMenuModel;

public class MainMenuController {

    @FXML
    private Button shoppingCartButton;
    @FXML
    private Button logOutButton;
    @FXML
    private Button goToProductsButton;
    @FXML
    private Button goToActivitiesButton;
    @FXML
    private Text userText;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void initialize() {
        String name = MainMenuModel.getName();
        // just in case getName returns null
        if (name == null)
            name = "User";
        userText.setText(name);
    }

    public void switchToLoginScene(ActionEvent event) throws IOException {
        this.root = FXMLLoader.load(getClass().getResource("../view/LoginView.fxml"));
        this.stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        this.scene = new Scene(this.root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToProductsScene(ActionEvent event) throws IOException {
        this.root = FXMLLoader.load(getClass().getResource("../view/ProductsView.fxml"));
        this.stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        this.scene = new Scene(this.root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToActivitiesScene(ActionEvent event) throws IOException {
        this.root = FXMLLoader.load(getClass().getResource("../view/ActivitiesView.fxml"));
        this.stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        this.scene = new Scene(this.root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToShoppingCartScene(ActionEvent event) throws IOException {
        this.root = FXMLLoader.load(getClass().getResource("../view/ShoppingCart.fxml"));
        this.stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        this.scene = new Scene(this.root);
        stage.setScene(scene);
        stage.show();
}
}
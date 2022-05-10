package control;

import javafx.stage.Stage;
import model.LoginModel;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

//Controller that takes cares of all the user interaction with LoginView.
public class LoginController {

    @FXML
    private Button singUpButton;
    @FXML
    private Button logInButton;
    @FXML
    private TextField broncoIDTextField;
    @FXML
    private PasswordField passwordField;

    public static String broncoID;

    private Stage stage;
    private Scene scene;
    private Parent root;

    // Clicking on signUpButton switches scene to the SignUpView.
    public void switchToSignUpScene(ActionEvent event) throws IOException {
        this.root = FXMLLoader.load(getClass().getResource("../view/SignUpView.fxml"));
        this.stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        this.scene = new Scene(this.root);
        stage.setScene(scene);
        stage.show();
    }

    // Switch to MainMenuView if user successfully logs in.
    public void switchToMainMenuScene(ActionEvent event) throws IOException {
        System.out.println("User successfully logs in!");
        this.root = FXMLLoader.load(getClass().getResource("../view/MainMenuView.fxml"));
        this.stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        this.scene = new Scene(this.root);
        stage.setScene(scene);
        stage.show();
        //MainMenuController.updateWelcomeText();
    }

    // Click on logInButton checks credentials through LoginModel and acts
    // accordingly.
    public void clickLogIn(ActionEvent event) throws IOException {

        // check if username field is empty and alert user about it
        if (broncoIDTextField.getText() == "") {
            Alert alert = new Alert(AlertType.ERROR);
            alert.getDialogPane().setContentText("Username field empty!");
            alert.getDialogPane().setHeaderText("Blank field");
            alert.showAndWait();
        }

        else if (passwordField.getText() == "") {
            Alert alert = new Alert(AlertType.ERROR);
            alert.getDialogPane().setContentText("Password field empty!");
            alert.getDialogPane().setHeaderText("Blank field");
            alert.showAndWait();
        }

        else if (LoginModel.checkCredentials(broncoIDTextField.getText(), passwordField.getText())) {
            broncoID = broncoIDTextField.getText();
            switchToMainMenuScene(event);
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.getDialogPane().setContentText("Username and password don't match.");
            alert.getDialogPane().setHeaderText("Credentials mismatch");
            alert.showAndWait();
        }
    }

}

package control;

import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.classes.Customer;
import model.SignUpModel;

public class SignUpController {

    // FXML links
    @FXML
    private Button singUMeUpButton;
    @FXML
    private Button goBackButton;
    @FXML
    private TextField broncoIDTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField fullNameTextField;
    @FXML
    private TextField phoneNumberTextField;
    @FXML
    private TextField streetTextField;
    @FXML
    private TextField zipCodeTextField;
    @FXML
    private TextField cityTextField;
    @FXML
    private TextField dobTextField;
    @FXML
    private TextField stateTextField;
    @FXML
    private CheckBox professorCheckBox;
    @FXML
    private CheckBox studentCheckBox;

    // variables
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void SwitchToLoginScene(ActionEvent event) throws IOException {
        this.root = FXMLLoader.load(getClass().getResource("../view/LogInView.fxml"));
        this.stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        this.scene = new Scene(this.root);
        stage.setScene(scene);
        stage.show();
    }

    public void signMeUp(ActionEvent event) throws IOException {
        boolean valid = true;
        int broncoID = -1;
        String broncoIDText = broncoIDTextField.getText();
        // make sure broncoID is not empty and only has digits
        if (broncoIDText == null || broncoIDText.equals("")) {
            valid = false;
            System.out.println("Blank broncoID");
        }
        else if (!broncoIDText.matches("[0-9]+")) {
            valid = false;
            System.out.println("BroncoID must only contain digits");
        }
        else {
            broncoID = Integer.parseInt(broncoIDText);
        }
        // check if broncoID actually changed (could be a JUnit test?)
        if (broncoID == -1) {
            valid = false;
            System.out.println("BroncoID returned -1");
        }
        String fullName = fullNameTextField.getText();
        // make sure Full Name is not blank
        if (fullName == null || fullName.equals("")) {
            valid = false;
            System.out.println("Blank Full Name");
        }
        // make sure Full Name only contains letters
        else if (!fullName.matches("[ a-zA-Z]+")) {
            valid = false;
            System.out.println("Full Name must only contain letters");
        }

        String password = passwordTextField.getText();
        // make sure password is not blank
        if (password == null || password.equals("")) {
            valid = false;
            System.out.println("Blank password");
        }
        else if (!password.matches("[a-zA-Z0-9]+")) {
            valid = false;
            System.out.println("Password must only contain letters and numbers");
        }
        
        String phoneNumber = phoneNumberTextField.getText();
        // make sure phoneNumber is not blank
        if (phoneNumber == null || phoneNumber.equals("")) {
            valid = false;
            System.out.println("Blank phone number");
        }
        // make sure phoneNumber is only digits
        else if (phoneNumber.matches("[0-9]+")) {
            valid = false;
            System.out.println("Phone number must only be digits");
        }

        String street = streetTextField.getText();
        String city = cityTextField.getText();
        String state = stateTextField.getText();
        int zipCode = Integer.valueOf(zipCodeTextField.getText());
        String DOB = dobTextField.getText();
        Customer.Status status = null;

        // need to have student or professor checked
        boolean statusCheck = false;

        if (professorCheckBox.isSelected() && studentCheckBox.isSelected()) {
            status = Customer.Status.BOTH;
            statusCheck = true;
        } else if (professorCheckBox.isSelected()) {
            status = Customer.Status.PROFESSOR;
            statusCheck = true;
        } else if (studentCheckBox.isSelected()) {
            status = Customer.Status.STUDENT;
            statusCheck = true;
        } else {
            System.out.println("ur an idiot lol");
        }
        if (valid && statusCheck) {
            Customer customer = new Customer(broncoID, password, fullName, phoneNumber, street, city, state, zipCode, DOB);
            customer.setStatus(status);
            customer.setDiscountScheme(status);
            SignUpModel.addCustomer(customer);
            SwitchToLoginScene(event);
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.getDialogPane().setContentText("Must choose student and/or professor");
            alert.getDialogPane().setHeaderText("Invalid status");
            alert.showAndWait();
        }

    }

}

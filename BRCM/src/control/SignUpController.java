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
        Alert alert = null;
        boolean valid = true;
        int broncoID = -1;
        String broncoIDText = broncoIDTextField.getText();
        // make sure broncoID is not empty and only has digits
        if (broncoIDText == null || broncoIDText.equals("")) {
            valid = false;
            if (alert == null) {
                alert = new Alert(AlertType.ERROR);
                alert.getDialogPane().setContentText("BroncoID is blank");
                alert.getDialogPane().setHeaderText("Invalid BroncoID");
            }
        }
        else if (!broncoIDText.matches("[0-9]+")) {
            valid = false;
            if (alert == null) {
                alert = new Alert(AlertType.ERROR);
                alert.getDialogPane().setContentText("BroncoID must only contain digits");
                alert.getDialogPane().setHeaderText("Invalid BroncoID");
            }
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
            if (alert == null) {
                alert = new Alert(AlertType.ERROR);
                alert.getDialogPane().setContentText("Full name is blank");
                alert.getDialogPane().setHeaderText("Invalid Full name");
            }
        }
        // make sure Full Name only contains letters
        else if (!fullName.matches("[ a-zA-Z]+")) {
            valid = false;
            if (alert == null) {
                alert = new Alert(AlertType.ERROR);
                alert.getDialogPane().setContentText("Full name must only contain letters");
                alert.getDialogPane().setHeaderText("Invalid Full name");
            }
        }

        String password = passwordTextField.getText();
        // make sure password is not blank
        if (password == null || password.equals("")) {
            valid = false;
            if (alert == null) {
                alert = new Alert(AlertType.ERROR);
                alert.getDialogPane().setContentText("Password is blank");
                alert.getDialogPane().setHeaderText("Invalid Password");
            }
        }
        else if (!password.matches("[a-zA-Z0-9]+")) {
            valid = false;
            if (alert == null) {
                alert = new Alert(AlertType.ERROR);
                alert.getDialogPane().setContentText("Password must only contain letters and digits");
                alert.getDialogPane().setHeaderText("Invalid Password");
            }
        }

        String DOB = dobTextField.getText();
        // make sure DOB is not blank
        if (DOB == null || DOB.equals("")) {
            valid = false;
            if (alert == null) {
                alert = new Alert(AlertType.ERROR);
                alert.getDialogPane().setContentText("DOB is blank");
                alert.getDialogPane().setHeaderText("Invalid DOB");
            }
        }

        // make sure DOB is in correct format (YEAR-MM-DD)
        else if (!DOB.matches("((?:19|20)\\d\\d)-(0?[1-9]|1[012])-([12][0-9]|3[01]|0?[1-9])")) {
            valid = false;
            if (alert == null) {
                alert = new Alert(AlertType.ERROR);
                alert.getDialogPane().setContentText("DOB must be a valid YYYY-MM-DD");
                alert.getDialogPane().setHeaderText("Invalid DOB");
            }
        }
        
        String phoneNumber = phoneNumberTextField.getText();
        // make sure phoneNumber is not blank
        if (phoneNumber == null || phoneNumber.equals("")) {
            valid = false;
            if (alert == null) {
                alert = new Alert(AlertType.ERROR);
                alert.getDialogPane().setContentText("Phone Number is blank");
                alert.getDialogPane().setHeaderText("Invalid Phone Number");
            }
        }
        // make sure phoneNumber is only digits
        else if (!phoneNumber.matches("[0-9]+")) {
            valid = false;
            if (alert == null) {
                alert = new Alert(AlertType.ERROR);
                alert.getDialogPane().setContentText("Phone Number must only contain digits");
                alert.getDialogPane().setHeaderText("Invalid Phone Number");
            }
        }

        String street = streetTextField.getText();
        // make sure street is not blank
        if (street == null || street.equals("")) {
            valid = false;
            if (alert == null) {
                alert = new Alert(AlertType.ERROR);
                alert.getDialogPane().setContentText("Street is blank");
                alert.getDialogPane().setHeaderText("Invalid Street");
            }
        }
        // make sure street is only letters, numbers, and spaces
        else if (!street.matches("[ a-zA-Z0-9]+")) {
            valid = false;
            if (alert == null) {
                alert = new Alert(AlertType.ERROR);
                alert.getDialogPane().setContentText("Street must only contain letters, spaces, and digits");
                alert.getDialogPane().setHeaderText("Invalid Street");
            }
        }

        String state = stateTextField.getText();
        // make sure state is not blank
        if (state == null || state.equals("")) {
            valid = false;
            if (alert == null) {
                alert = new Alert(AlertType.ERROR);
                alert.getDialogPane().setContentText("State is blank");
                alert.getDialogPane().setHeaderText("Invalid State");
            }
        }
        // make sure state is only letters
        else if (!state.matches("[a-zA-Z]+")) {
            valid = false;
            if (alert == null) {
                alert = new Alert(AlertType.ERROR);
                alert.getDialogPane().setContentText("State must only contain letters");
                alert.getDialogPane().setHeaderText("Invalid State");
            }
        }

        String city = cityTextField.getText();
        // make sure city is not blank
        if (city == null || city.equals("")) {
            valid = false;
            if (alert == null) {
                alert = new Alert(AlertType.ERROR);
                alert.getDialogPane().setContentText("City is blank");
                alert.getDialogPane().setHeaderText("Invalid City");
            }
        }
        // make sure city is only letters
        else if (!city.matches("[a-zA-Z]+")) {
            valid = false;
            if (alert == null) {
                alert = new Alert(AlertType.ERROR);
                alert.getDialogPane().setContentText("City must only contain letters");
                alert.getDialogPane().setHeaderText("Invalid City");
            }
        }

        int zipCode = -1;
        String zipCodeText = zipCodeTextField.getText();
        // make sure zipCode is not empty and only has digits
        if (zipCodeText == null || zipCodeText.equals("")) {
            valid = false;
            if (alert == null) {
                alert = new Alert(AlertType.ERROR);
                alert.getDialogPane().setContentText("Zip Code is blank");
                alert.getDialogPane().setHeaderText("Invalid Zip Code");
            }
        }
        // make sure zipCode only contains digits
        else if (!zipCodeText.matches("[0-9]+")) {
            valid = false;
            if (alert == null) {
                alert = new Alert(AlertType.ERROR);
                alert.getDialogPane().setContentText("Zip Code must only contain digits");
                alert.getDialogPane().setHeaderText("Invalid Zip Code");
            }
        }
        else {
            zipCode = Integer.parseInt(zipCodeText);
        }
        // check if zipCode actually changed (could be a JUnit test?)
        if (zipCode == -1) {
            valid = false;
            System.out.println("zipCode returned -1");
        }
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
        // BREAKS FOR NOW
        if (valid && statusCheck) {
            Customer customer = new Customer(broncoID, password, fullName, phoneNumber, street, city, state, zipCode, DOB);
            customer.setStatus(status);
            customer.setDiscountScheme(status);
            SignUpModel.addCustomer(customer);
            SwitchToLoginScene(event);
        } else {
            if (alert == null) {
                alert = new Alert(AlertType.ERROR);
                alert.getDialogPane().setContentText("Must choose student and/or professor");
                alert.getDialogPane().setHeaderText("Invalid status");
            }
            alert.showAndWait();
        }

    }

}

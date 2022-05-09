package control;

import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
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

    public void signMeUp(ActionEvent event) throws IOException{
        int broncoID = Integer.valueOf(broncoIDTextField.getText());
        String fullName = fullNameTextField.getText();
        String password = passwordTextField.getText();
        String phoneNumber = phoneNumberTextField.getText();
        String street = streetTextField.getText();
        String city = cityTextField.getText();
        String state = stateTextField.getText();
        int zipCode = Integer.valueOf(zipCodeTextField.getText());
        String DOB = dobTextField.getText();
        Customer.Status status = null;

        if (professorCheckBox.isSelected() && studentCheckBox.isSelected()){
            status = Customer.Status.BOTH;
        } else if (professorCheckBox.isSelected()) {
            status = Customer.Status.PROFESSOR;
        } else if (studentCheckBox.isSelected()) {
            status = Customer.Status.STUDENT;
        } else {
           System.out.println("ur an idiot lol");
        }


        Customer customer = new Customer(broncoID, password, fullName, phoneNumber, street, city, state, zipCode, DOB);
        customer.setStatus(status);
        customer.setDiscountScheme(status);
        SignUpModel.addCustomer(customer);

        SwitchToLoginScene(event);

    }

}

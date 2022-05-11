package control;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import model.ActivitiesModel;
import model.ProductsModel;
import model.classes.Activity;
import model.classes.Product;

import javafx.scene.control.TextField;

public class ActivitiesController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button goBackButton;
    @FXML
    private Button shoppingCartButton;
    @FXML
    private Button logOutButton;

    @FXML
    private TextField searchActivityTextField;

    @FXML
    private TableView<Activity> activitiesTableView;
    @FXML
    private TableColumn<Activity, String> activityTableColumn;
    @FXML
    private TableColumn<Activity, DatePicker> datesDateColumn;
    @FXML
    private TableColumn<Activity, Float> priceTableColumn;
    @FXML
    private TableColumn<Activity, Button> historicalPricesColumn;

    ObservableList<Activity> activityObservableList = FXCollections.observableArrayList();

    @FXML
    public void initialize() throws SQLException {
        updateActvityTable();
    }

    public void updateActvityTable() throws SQLException {
        ResultSet activities = ActivitiesModel.getListOfActivities();

        while(activities.next()){
            Integer activityID = activities.getInt("productID");
            String productName = activities.getString("name");
            System.out.println(productName);
            Float productPrice = activities.getFloat("currentPrice");
            System.out.println(productPrice);

            activityObservableList.add(new Activity(activityID, productName, productPrice));
        }
        activityTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceTableColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceTableColumn.setStyle("-fx-alignment: CENTER;");

        activitiesTableView.setItems(activityObservableList);
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

    // Switch to MainMenuView when backArrow is clicked
    public void switchToMainMenuScene(ActionEvent event) throws IOException {
        System.out.println("Back to Main Menu!");
        this.root = FXMLLoader.load(getClass().getResource("../view/MainMenuView.fxml"));
        this.stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        this.scene = new Scene(this.root);
        stage.setScene(scene);
        stage.show();
    }
}

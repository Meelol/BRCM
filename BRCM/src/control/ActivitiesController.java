package control;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import model.classes.Cart;
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
    private TableColumn<Activity, Button> datesTableColumn;
    @FXML
    private TableColumn<Activity, Float> priceTableColumn;
    @FXML
    private TableColumn<Activity, Button> historicalPricesColumn;

    ObservableList<Activity> activityObservableList = FXCollections.observableArrayList();

    @FXML
    public void initialize() throws SQLException {
        updateActivityTable();
    }

    public void updateActivityTable() throws SQLException {
        ResultSet activities = ActivitiesModel.getListOfActivities();
        ResultSet activityDate = ActivitiesModel.getListOfActivityDates();
        while(activities.next()){
            Integer activityID = activities.getInt("productID");
            String productName = activities.getString("name");
            System.out.println(productName);
            Float productPrice = activities.getFloat("currentPrice");
            System.out.println(productPrice);
            String date = "";
            if(activityDate.next()){
                date = activityDate.getString("date");
            }
            Button button = new Button("");
            Activity activity = new Activity(activityID, productName, productPrice, date, button);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Cart.addActivityToCart(activity);
                    Cart.printCart();
                }
            });
            activityObservableList.add(activity);
        }
        activityTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceTableColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceTableColumn.setStyle("-fx-alignment: CENTER;");
        datesTableColumn.setCellValueFactory(new PropertyValueFactory<>(("button")));
        datesTableColumn.setStyle("-fx-alignment: CENTER;");

        activitiesTableView.setItems(activityObservableList);

        // Search Activity Implementation
        FilteredList<Activity> filteredObservableList = new FilteredList<>(activityObservableList, b -> true);
        searchActivityTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredObservableList.setPredicate(Activity -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }

                String searchKeyword = newValue.toLowerCase();

                if (Activity.getName().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else
                    return false;

            });
        });

        SortedList<Activity> sortedData = new SortedList<>(filteredObservableList);

        sortedData.comparatorProperty().bind(activitiesTableView.comparatorProperty());
        activitiesTableView.setItems(sortedData);
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

    // Switch to MainMenuView when Shopping Cart Icon is clicked
    public void switchToShoppingCartScene(ActionEvent event) throws IOException {
        System.out.println("Shopping Cart!");
        this.root = FXMLLoader.load(getClass().getResource("../view/ShoppingCartView.fxml"));
        this.stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        this.scene = new Scene(this.root);
        stage.setScene(scene);
        stage.show();
        }
}

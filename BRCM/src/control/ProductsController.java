package control;

import java.sql.ResultSet;
import java.sql.SQLException;

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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import model.ProductsModel;
import model.classes.Product;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class ProductsController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField searchFoodTextField;
    @FXML
    private TextField searchClothesTextField;
    @FXML
    private Button goBackButton;
    @FXML
    private Button logOutButton;
    @FXML
    private TableView<Product> productsTableView;
    @FXML
    private TableColumn<Product, String> nameTableColumn;
    @FXML
    private TableColumn<Product, Float> priceTableColumn;
    @FXML
    private TableColumn<Product, String> quantityTableColumn;
    @FXML
    private TableColumn<Product, Button> addToCartTableColumn;

    @FXML
    private TableView<Product> productsTableView1;
    @FXML
    private TableColumn<Product, String> nameTableColumn1;
    @FXML
    private TableColumn<Product, Float> priceTableColumn1;
    @FXML
    private TableColumn<Product, String> quantityTableColumn1;
    @FXML
    private TableColumn<Product, Button> addToCartTableColumn1;

    ObservableList<Product> productsObservableList = FXCollections.observableArrayList();
    ObservableList<Product> productsObservableList1 = FXCollections.observableArrayList();

    @FXML
    public void initialize() throws SQLException {
        UpdateFoodsTable();
        UpdateClothesTable();
    }

    public void UpdateFoodsTable() throws SQLException {
        ResultSet products = ProductsModel.getListOfFood();
        while (products.next()) {
            Integer productID = products.getInt("productID");
            System.out.println(productID);
            String productName = products.getString("name");
            System.out.println(productName);
            Float productPrice = products.getFloat("currentPrice");
            System.out.println(productPrice);
            productsObservableList.add(new Product(productID, productName, productPrice));
        }
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceTableColumn.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        priceTableColumn.setStyle("-fx-alignment: CENTER;");
        addToCartTableColumn.setCellValueFactory(new PropertyValueFactory<>("button"));
        addToCartTableColumn.setStyle("-fx-alignment: CENTER;");
        quantityTableColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        quantityTableColumn.setStyle("-fx-alignment: CENTER;");
        productsTableView.setItems(productsObservableList);
        productsTableView.setEditable(true);
        quantityTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        FilteredList<Product> filteredObservableList = new FilteredList<>(productsObservableList, b -> true);
        searchFoodTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredObservableList.setPredicate(Product -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }

                String searchKeyword = newValue.toLowerCase();

                if (Product.getName().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else
                    return false;

            });
        });

        SortedList<Product> sortedData = new SortedList<>(filteredObservableList);

        sortedData.comparatorProperty().bind(productsTableView.comparatorProperty());
        productsTableView.setItems(sortedData);
    }

    public void UpdateClothesTable() throws SQLException {
        ResultSet products = ProductsModel.getListOfClothes();
        while (products.next()) {
            Integer productID = products.getInt("productID");
            System.out.println(productID);
            String productName = products.getString("name");
            System.out.println(productName);
            Float productPrice = products.getFloat("currentPrice");
            System.out.println(productPrice);
            productsObservableList1.add(new Product(productID, productName, productPrice));
        }
        nameTableColumn1.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceTableColumn1.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        priceTableColumn1.setStyle("-fx-alignment: CENTER;");
        addToCartTableColumn1.setCellValueFactory(new PropertyValueFactory<>("button"));
        addToCartTableColumn1.setStyle("-fx-alignment: CENTER;");
        quantityTableColumn1.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        quantityTableColumn1.setStyle("-fx-alignment: CENTER;");
        productsTableView1.setItems(productsObservableList1);
        productsTableView1.setEditable(true);
        quantityTableColumn1.setCellFactory(TextFieldTableCell.forTableColumn());

        FilteredList<Product> filteredObservableList = new FilteredList<>(productsObservableList1, b -> true);
        searchClothesTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredObservableList.setPredicate(Product -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }

                String searchKeyword = newValue.toLowerCase();

                if (Product.getName().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else
                    return false;

            });
        });

        SortedList<Product> sortedData = new SortedList<>(filteredObservableList);

        sortedData.comparatorProperty().bind(productsTableView1.comparatorProperty());
        productsTableView1.setItems(sortedData);
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

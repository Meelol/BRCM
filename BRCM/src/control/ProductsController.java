package control;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.classes.Cart;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.ProductsModel;
import model.classes.Product;

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
    private Button shoppingCarButton;
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

    // initialize() is required to activate the sql populating into the tableView
    @FXML
    public void initialize() throws SQLException {
        UpdateFoodsTable();
        UpdateClothesTable();
    }

    // This method will update/populate the food products into foodTableView
    public void UpdateFoodsTable() throws SQLException {
        ResultSet products = ProductsModel.getListOfFood();
        while (products.next()) {
            Integer productID = products.getInt("productID");
            String productName = products.getString("name");
            Float productPrice = products.getFloat("currentPrice");
            Button button = new Button("");
            Image image = new Image("res/add-to-cart.png");
            ImageView view = new ImageView(image);
            view.setFitHeight(20);
            view.setPreserveRatio(true);
            button.setGraphic(view);
            button.setPrefSize(50, 20);
            Product product = new Product(productID, productName, productPrice, button);
            //Add to Cart Button Implementation
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Cart.addProductToCart(product, Integer.valueOf(product.getQuantity()));
                    Cart.printCart();
                }
            });
            productsObservableList.add(product);
        }
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceTableColumn.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        priceTableColumn.setStyle("-fx-alignment: CENTER;");
        addToCartTableColumn.setCellValueFactory(new PropertyValueFactory<>("button"));
        addToCartTableColumn.setStyle("-fx-alignment: CENTER;");
        quantityTableColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        quantityTableColumn.setStyle("-fx-alignment: CENTER;");
        productsTableView.setItems(productsObservableList);
        //Edit quantity value in TableView
        productsTableView.setEditable(true);
        quantityTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        quantityTableColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Product, String>>() {

            @Override
            public void handle(CellEditEvent<Product, String> event) {
                Product product = event.getRowValue();
                product.setQuantity(event.getNewValue());
            }
        });

        // This code allows for instant searching through food as you type in search bar
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

    // This method will update/populate the clothes products into clothesTableView
    public void UpdateClothesTable() throws SQLException {
        ResultSet products = ProductsModel.getListOfClothes();
        while (products.next()) {
            Integer productID = products.getInt("productID");
            String productName = products.getString("name");
            Float productPrice = products.getFloat("currentPrice");
            Button button = new Button("");
            Image image = new Image("res/add-to-cart.png");
            ImageView view = new ImageView(image);
            view.setFitHeight(20);
            view.setPreserveRatio(true);
            button.setGraphic(view);
            button.setPrefSize(50, 20);
            Product product = new Product(productID, productName, productPrice, button);
            //Add to Cart Button Implementation
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Cart.addProductToCart(product, Integer.valueOf(product.getQuantity()));
                    Cart.printCart();
                }
            });
            productsObservableList1.add(product);
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
        quantityTableColumn1.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Product, String>>() {

            @Override
            public void handle(CellEditEvent<Product, String> event) {
                Product product = event.getRowValue();
                product.setQuantity(event.getNewValue());
            }
        });

        // This code allows for instant searching through food as you type in search bar
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

    // Switch to MainMenuView when Shopping Cart Icon is clicked
    public void switchToShoppingCartscene(ActionEvent event) throws IOException {
        System.out.println("Shopping Cart!");
        this.root = FXMLLoader.load(getClass().getResource("../view/ShoppingCartView.fxml"));
        this.stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        this.scene = new Scene(this.root);
        stage.setScene(scene);
        stage.show();
    }
}

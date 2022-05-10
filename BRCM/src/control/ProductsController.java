package control;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ProductsModel;
import model.classes.Product;

public class ProductsController {

    @FXML
    private TableView<Product> productsTableView;
    @FXML
    private TableColumn<Product, String> nameTableColumn;
    @FXML
    private TableColumn<Product, Float> priceTableColumn;
    @FXML
    private TableColumn<Product, Integer> quantityTableColumn;
    @FXML
    private TableColumn<Product, Button> AddToCartTableColumn;

    ObservableList<Product> productsObservableList = FXCollections.observableArrayList();

    @FXML
    public void initialize() throws SQLException {
        UpdateProductsTable();
    }

    public void UpdateProductsTable() throws SQLException {
        ResultSet products = ProductsModel.getListOfProducts();
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
        productsTableView.setItems(productsObservableList);
    }
}

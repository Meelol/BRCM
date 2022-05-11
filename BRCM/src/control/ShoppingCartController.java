package control;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.ShoppingCartModel;
import model.classes.Activity;
import model.classes.Cart;
import model.classes.Product;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ShoppingCartController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableView<ShoppingCartModel> shoppingCartTableView;
    @FXML
    private TableColumn<ShoppingCartModel, Button> removeFromCartTableColumn;
    @FXML
    private TableColumn<ShoppingCartModel, String> productTableColumn;
    @FXML
    private TableColumn<ShoppingCartModel, Float> priceTableColumn;
    @FXML
    private TableColumn<ShoppingCartModel, Integer> quantityTableColumn;
    @FXML
    private TableColumn<ShoppingCartModel, Float> subtotalTableColumn;
    @FXML
    private Button goBackButton;
    @FXML
    private Button logOutButton;

    ObservableList<ShoppingCartModel> cartObservableList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        Cart.printCart();
        updateCartTable();
    }

    public void updateCartTable() {
        for (Product product : Cart.cart.getProductQuantity().keySet()) {
            String productName = product.getName();
            int quantity = Integer.valueOf(product.getQuantity());
            float price = product.getUnitPrice();
            float subtotal = (float) quantity * price;
            System.out.println("Subtotal: " + subtotal);
            Button button = new Button("");
            Image image = new Image("res/eliminar.png");
            ImageView view = new ImageView(image);
            view.setFitHeight(20);
            view.setPreserveRatio(true);
            button.setGraphic(view);
            button.setPrefSize(50, 20);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Cart.removeProductFromCart(product);
                    try {
                        refreshTable(event);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });
            cartObservableList.add(new ShoppingCartModel(button, productName, quantity, price, subtotal));
        }
        for (Activity activity : Cart.cart.getActivityQuantity().keySet()) {
            String productName = activity.getName();
            int quantity = Cart.cart.getActivityQuantity().get(activity);
            float price = activity.getPrice();
            float subtotal = (float) quantity * price;
            System.out.println("Subtotal: " + subtotal);
            Button button = new Button("");
            Image image = new Image("res/eliminar.png");
            ImageView view = new ImageView(image);
            view.setFitHeight(20);
            view.setPreserveRatio(true);
            button.setGraphic(view);
            button.setPrefSize(50, 20);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Cart.removeActivityFromCart(activity);
                    try {
                        refreshTable(event);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });
            cartObservableList.add(new ShoppingCartModel(button, productName, quantity, price, subtotal));
        }

        removeFromCartTableColumn.setCellValueFactory(new PropertyValueFactory<>("button"));
        productTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceTableColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityTableColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        subtotalTableColumn.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
        shoppingCartTableView.setItems(cartObservableList);
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

    public void refreshTable(ActionEvent event) throws IOException {
        System.out.println("Logging Out!");
        this.root = FXMLLoader.load(getClass().getResource("../view/ShoppingCartView.fxml"));
        this.stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        this.scene = new Scene(this.root);
        stage.setScene(scene);
        stage.show();
    }

    // Switch to MainMenuView when backArrow is clicked
    public void switchToProductsScene(ActionEvent event) throws IOException {
        System.out.println("Back to Products!");
        this.root = FXMLLoader.load(getClass().getResource("../view/ProductsView.fxml"));
        this.stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        this.scene = new Scene(this.root);
        stage.setScene(scene);
        stage.show();
    }
}

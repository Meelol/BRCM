package model;

import javafx.scene.control.Button;
import model.classes.Cart;

public class ShoppingCartModel {

    private String productName;
    private int quantity;
    private float price;
    private float subtotal;
    private Button button;

    public ShoppingCartModel(Button button, String productName, int quantity, float price, float subtotal) {
        this.button = button;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.subtotal = subtotal;
    }

    public String getName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public Button getButton() {
        return button;
    }
}

package model.classes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import control.MainMenuController;

public class Product {
    private int productID;
    private String name;
    private float unitPrice;

    private Button button = new Button();
    private String quantity;

    public Product(int productID, String name, float unitPrice, Button button) {
        this.productID = productID;
        this.name = name;
        this.unitPrice = unitPrice;
        this.button = button;
        this.quantity = "0";
    }

    public float getUnitPrice() {
        return this.unitPrice;
    }

    public String getName() {
        return this.name;
    }

    public Button getButton() {
        return this.button;
    }

    public String getQuantity() {
        return this.quantity;
    }
    public void setQuantity(String q){
        this.quantity = q;
    }
}

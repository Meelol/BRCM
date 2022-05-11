package model.classes;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Product {
    private int productID;
    private String name;
    private float unitPrice;

    private Button button;
    private String quantity;
    private Image image = new Image("res/add-to-cart.png");
    private ImageView view = new ImageView(image);

    public Product(int productID, String name, float unitPrice) {
        this.productID = productID;
        this.name = name;
        this.unitPrice = unitPrice;
        this.button = new Button("");
        view.setFitHeight(20);
        view.setPreserveRatio(true);
        button.setGraphic(view);
        button.setPrefSize(50, 20);
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
}

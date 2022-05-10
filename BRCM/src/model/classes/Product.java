package model.classes;
public class Product {
    private int productID;
    private String name;
    private float unitPrice;

    public Product(int productID, String name, float unitPrice) {
        this.productID = productID;
        this.name = name;
        this.unitPrice = unitPrice;
    }

    public float getUnitPrice() {
        return this.unitPrice;
    }

    public String getName(){
        return this.name;
    }

}

package model.classes;

import java.time.LocalTime;
import java.util.HashMap;
import java.time.LocalDate;

public class Order {
    private int orderID;
    private int broncoID;
    private LocalDate date;
    private LocalTime time;
    private float totalPrice;
    private HashMap<Product, Integer> products = new HashMap<Product, Integer>();

    private static int orderNumber = 0;

    public Order(int broncoID) {
        this.orderID = increaseOrderNum();
        this.broncoID = broncoID;
        this.date = LocalDate.now();
        this.time = LocalTime.now();
    }

    public void addProduct(Product product, int quantity) {
        this.products.put(product, quantity);
    }

    public float getTotalPrice() {
        this.totalPrice = 0;
        for (Product product : this.products.keySet()) {
            this.totalPrice += product.getUnitPrice() * this.products.get(product);
        }
        return this.totalPrice;
    }

    public int getOrderID() {
        return this.orderID;
    }

    public int increaseOrderNum() {
        Order.orderNumber++;
        return Order.orderNumber;
    }

}

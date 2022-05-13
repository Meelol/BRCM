package model.classes;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.time.LocalDate;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Order {
    private int orderID;
    private int broncoID;
    private LocalDate date;
    private LocalTime time;
    private float totalPrice;
    private HashMap<Product, Integer> products;
    private HashMap<Activity, Integer> activities;
    private float discount;

    public Order(int orderID, int broncoID, HashMap<Product, Integer> products, HashMap<Activity, Integer> activities,
            float discount) {
        this.orderID = orderID;
        this.broncoID = broncoID;
        this.date = LocalDate.now();
        this.time = LocalTime.now();
        this.products = products;
        this.activities = activities;
        totalPrice = getTotalPrice();
        this.discount = discount;
    }

    public HashMap<Product, Integer> getProducts() {
        return this.products;
    }

    public HashMap<Activity, Integer> getActivities() {
        return this.activities;
    }

    public float getTotalPrice() {
        this.totalPrice = 0;
        for (Product product : this.products.keySet()) {
            this.totalPrice += product.getUnitPrice() * this.products.get(product);
        }
        for (Activity activity : this.activities.keySet()) {
            this.totalPrice += activity.getPrice() * this.activities.get(activity);
        }
        this.totalPrice = this.totalPrice - (this.discount * this.totalPrice);
        return this.totalPrice;
    }

    public int getOrderID() {
        return this.orderID;
    }

    public int getBroncoID() {
        return this.broncoID;
    }

    public String getDate() {
        return date.toString();
    }

    public String getTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String timeString = time.format(formatter);
        return timeString;
    }

    public void printReceipt() {
        String receiptText = "\t\tOrder Summary\n \tCustomerID: " + this.getBroncoID() + " Order#: " + this.getOrderID()
                + "\n";
        float total = 0;
        for (Product product : this.products.keySet()) {
            String productName = product.getName();
            String quantity = String.valueOf(this.products.get(product));
            String price = String.valueOf(product.getUnitPrice() * this.products.get(product));
            total += Float.valueOf(price);
            receiptText += "\n\t" + productName + " " + quantity + " " + price + "$";
        }
        for (Activity activity : this.activities.keySet()) {
            String productName = activity.getName();
            String price = String.valueOf(activity.getPrice());
            total += Float.valueOf(price);
            receiptText += " \n\t" + productName + " " + price + "$";
        }
        receiptText += "\n\n\tTotal: " + total + "$\n\tDiscount: " + discount + "%" + "\n\tTotal after Disc.: "
                + (total - (total * discount)) + "$\n\n";
        receiptText += "\t" + this.getDate() + " " + this.getTime() + "\n";
        File receipt = new File("./Receipts/filename.txt");
        try {
            FileWriter myWriter = new FileWriter(receipt);
            myWriter.write(receiptText);
            myWriter.close();
            System.out.println("Receipt successfully created!");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

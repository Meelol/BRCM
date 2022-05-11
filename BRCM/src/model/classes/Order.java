package model.classes;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.time.LocalDate;

import java.util.Random;

public class Order {
    private int orderID;
    private int broncoID;
    private LocalDate date;
    private LocalTime time;
    private float totalPrice;
    private HashMap<Product, Integer> products;
    private HashMap<Activity, Integer> activities;
    private float discount;

    public Order(int broncoID, HashMap<Product, Integer> products, HashMap<Activity, Integer> activities,
            float discount) {
        Random rand = new Random();
        orderID = rand.nextInt(1000000);
        this.broncoID = broncoID;
        this.date = LocalDate.now();
        this.time = LocalTime.now();
        this.products = products;
        this.activities = activities;
        totalPrice = getTotalPrice();
        this.discount = discount;
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
        float total = 0;
        System.out.println("\n\n\n");
        for (Product product : this.products.keySet()) {
            String productName = product.getName();
            String quantity = String.valueOf(this.products.get(product));
            String price = String.valueOf(product.getUnitPrice() * this.products.get(product));
            total += Float.valueOf(price);
            System.out.println(productName + " " + quantity + " " + price);
        }
        for (Activity activity : this.activities.keySet()) {
            String productName = activity.getName();
            String price = String.valueOf(activity.getPrice());
            total += Float.valueOf(price);
            System.out.println(productName + " " + price);
        }
        System.out.println("Total: " + total + "$\nDiscount: " + discount + "%" + "\nTotal after Disc.: "
                + (total - (total * discount)) + "$\n\n\n");
    }

}

package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.scene.control.Button;
import model.classes.Order;
import model.classes.Product;
import model.classes.Activity;

public class ShoppingCartModel {

    private String productName;
    private int quantity;
    private float price;
    private float subtotal;
    private Button button;

    static Connection conn = null;

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

    public static float getDiscountScheme(String broncoID) {
        conn = DBConnect.startConnection();
        float discountScheme = 0;
        try {
            Statement stmnt = conn.createStatement();
            String sql = "SELECT \"discountScheme\" FROM public.\"CUSTOMERS\" WHERE \"broncoID\"=" + broncoID + ";";
            ResultSet rs = stmnt.executeQuery(sql);
            while (rs.next()) {
                discountScheme = rs.getFloat("discountScheme");
            }
            conn.close();
            return discountScheme;
        } catch (Exception e) {
            return 0;
        }
    }

    public static int getCurrentOrderNum(){
        conn = DBConnect.startConnection();
        int currentOrderNum = 0;
        try {
            Statement stmnt = conn.createStatement();
            String sql = "SELECT MAX(\"orderID\") FROM public.\"ORDER\"";
            ResultSet rs = stmnt.executeQuery(sql);
            while (rs.next()) {
                currentOrderNum = rs.getInt("max");
            }
            conn.close();
            return currentOrderNum;
        } catch (Exception e) {
            return 1;
        }
    }
    public static void insertOrderToDB(Order order) {
        conn = DBConnect.startConnection();

        try {
            Statement stmnt = conn.createStatement();
            String insertOrder = "INSERT INTO public.\"ORDER\"(\"orderID\", \"broncoID\", date, time, \"totalPrice\") VALUES ("
                    + order.getOrderID() + ", " + order.getBroncoID() + ", \'" + order.getDate() + "\', \'"
                    + order.getTime() + "\', " + order.getTotalPrice() + ");";
            stmnt.executeUpdate(insertOrder);
            for (Product product : order.getProducts().keySet()) {
                String insertProductsToPQ = "INSERT INTO public.\"productQuantity\"("
                        + "\"productID\", \"orderID\", quantity) VALUES (" + product.getProductID() + ", "
                        + order.getOrderID() + ", " + order.getProducts().get(product) + ")";
                stmnt.executeUpdate(insertProductsToPQ);
            }
            for (Activity activity : order.getActivities().keySet()) {
                String insertActivitiesToPQ = "INSERT INTO public.\"productQuantity\"("
                        + "\"productID\", \"orderID\", quantity) VALUES (" + activity.getID() + ", "
                        + order.getOrderID() + ", " + order.getActivities().get(activity) + ")";
                stmnt.executeUpdate(insertActivitiesToPQ);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

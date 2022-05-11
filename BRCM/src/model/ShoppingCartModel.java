package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.scene.control.Button;
import model.classes.Order;

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
            System.out.println(sql);
            ResultSet rs = stmnt.executeQuery(sql);
            while (rs.next()) {
                discountScheme = rs.getFloat("discountScheme");
            }
            return discountScheme;
        } catch (Exception e) {
            return 0;
        }
    }

    public static void insertOrderToDB(Order order){
        conn = DBConnect.startConnection();

        try {
            String insertCustomersql = "INSERT INTO public.\"ORDER\"(\"orderID\", \"broncoID\", date, time, \"totalPrice\") VALUES ("
                + order.getOrderID() + ", " + order.getBroncoID() + ", \'" + order.getDate() + "\', \'"
                + order.getTime() + "\', " + order.getTotalPrice() +");";
            System.out.println(insertCustomersql);
            Statement stmnt = conn.createStatement();
            stmnt.executeUpdate(insertCustomersql);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

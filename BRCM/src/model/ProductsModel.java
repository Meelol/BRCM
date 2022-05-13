package model;

import java.sql.*;

public class ProductsModel {

    static Connection conn = null;

    public static ResultSet getListOfFood() {
        conn = DBConnect.startConnection();
        String query = "SELECT * FROM public.\"PRODUCTS\" AS P WHERE P.\"productID\" < 2000 AND P.\"productID\" >= 1000 ;";
        try {
            Statement stmnt = conn.createStatement();
            ResultSet set = stmnt.executeQuery(query);
            conn.close();
            return set;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ResultSet getListOfClothes() {
        conn = DBConnect.startConnection();
        String query = "SELECT * FROM public.\"PRODUCTS\" AS P WHERE P.\"productID\" >= 2000 AND P.\"productID\" < 3000;";
        try {
            Statement stmnt = conn.createStatement();
            ResultSet set = stmnt.executeQuery(query);
            conn.close();
            return set;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

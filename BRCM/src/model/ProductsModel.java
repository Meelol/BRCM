package model;
import java.sql.*;
import model.classes.Product;
public class ProductsModel {

    static Connection conn = null;
    public static ResultSet getListOfProducts(){
        conn = DBConnect.startConnection();
        String query = "SELECT name, \"currentPrice\" FROM public.\"PRODUCTS\";";
        try{
            Statement stmnt = conn.createStatement();
            ResultSet set = stmnt.executeQuery(query);
            return set;

        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

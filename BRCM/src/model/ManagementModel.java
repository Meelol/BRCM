package model;

import java.sql.*;

public class ManagementModel {
    static Connection conn = null;
    public static ResultSet getOrders(){
        conn = DBConnect.startConnection();
        try{
            
            Statement stmnt = conn.createStatement();
            String sql = "SELECT \"totalPrice\" FROM public.\"ORDER\";";
            ResultSet rs = stmnt.executeQuery(sql);
            conn.close();
            return rs;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

package model;
import java.sql.*;

import control.LoginController;

public class MainMenuModel {

    public static Connection conn = null;

    public static String getName(){
        conn = DBConnect.startConnection();
        String user = "default";
        try{
            
            Statement stmnt1 = conn.createStatement();
            String sql1 = "SELECT * FROM public.\"CUSTOMERS\" WHERE \"broncoID\"="+ LoginController.broncoID +"";
            ResultSet rs1 = stmnt1.executeQuery(sql1);
            System.out.println(sql1);
            while (rs1.next()) {
                user = rs1.getString("name");
                System.out.println("Name " + user);
            }
            return user;

        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}
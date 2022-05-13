package model;

import java.sql.*;
import model.classes.Customer;
import model.classes.Customer.Status;

public class LoginModel {
    static Connection conn = null;
    Customer.Status s = Status.BOTH;

    public static boolean checkCredentials(String username, String password){
        conn = DBConnect.startConnection();
        try{
            Statement stmnt = conn.createStatement();
            String sql = "SELECT * FROM public.\"CUSTOMERS\" WHERE \"broncoID\"="+username+" AND \"password\"="+"\'"+password+"\'";
            ResultSet rs = stmnt.executeQuery(sql);
            conn.close();
            return rs.next();
            
        }catch(Exception e){
            return false;
        }
    }

    public static boolean isStaff(String username, String password){
        conn = DBConnect.startConnection();
        try{
            Statement stmnt = conn.createStatement();
            String sql = "SELECT * FROM public.\"CUSTOMERS\" WHERE \"broncoID\"="+username+" AND \"password\"="+"\'"+password+"\' AND \"status\"="+3;
            ResultSet rs = stmnt.executeQuery(sql);
            conn.close();
            return rs.next();
            
        }catch(Exception e){
            return false;
        }
    }
}

package model;

import java.sql.*;

public class LoginModel {
    static Connection conn = null;

    public static boolean checkCredentials(String username, String password){
        conn = DBConnect.startConnection();
        try{
            Statement stmnt = conn.createStatement();
            String sql = "SELECT * FROM public.\"CUSTOMERS\" WHERE \"broncoID\"="+username+" AND \"password\"="+"\'"+password+"\'";
            ResultSet rs = stmnt.executeQuery(sql);
            if(rs.next()){
                return true;
            }
            else return false;
            
        }catch(Exception e){
            return false;
        }
    }
}

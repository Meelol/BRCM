package model;

import java.sql.*;

public class SignMeUpModel {
    static Connection conn = null;

    public static void insertCredentials(){
        conn = DBConnect.startConnection();

        try{
            Statement stmnt = conn.createStatement();

        } catch(Exception e){

        }
        
    }
    
}
package model;

import java.sql.*;

import model.classes.Customer;

public class SignUpModel {
    static Connection conn = null;

    public static void addCustomer(Customer customer){
        conn = DBConnect.startConnection();

        
        String insertCustomersql = "INSERT INTO public.\"CUSTOMERS\"( \"broncoID\", name, password, \"phoneNumber\", \"DOB\", \"discountScheme\", status) VALUES ("
                    + customer.getBroncoID() + ", \'" + customer.getName() + "\', \'"+ customer.getPassword() + "\', "+ customer.getPhoneNumber() 
                    + ", \'"+ customer.getDOB() + "\', " + customer.getDiscountScheme() + ", "+ customer.getStatus() + ");";
        String insertAddresssql = "INSERT INTO public.\"Address\"(\"broncoID\", street, \"zipCode\", city, state) VALUES (" 
                                  + customer.getBroncoID() + ",\'" + customer.getStreet() + "\'," + customer.getZipCode() 
                                  + ", \'" + customer.getCity() + "\', \'" + customer.getState() + "\');";
        System.out.println("Customer added successfully!");  

        try{
            Statement stmnt = conn.createStatement();
            stmnt.executeUpdate(insertCustomersql);
            stmnt.executeUpdate(insertAddresssql);

        } catch(Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
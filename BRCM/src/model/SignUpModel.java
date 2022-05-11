package model;

import java.sql.*;

import model.classes.Customer;

public class SignUpModel {
    static Connection conn = null;

    public static boolean addCustomer(Customer customer) {
        conn = DBConnect.startConnection();

        String insertCustomersql = "INSERT INTO public.\"CUSTOMERS\"( \"broncoID\", name, password, \"phoneNumber\", \"DOB\", \"discountScheme\", status) VALUES ("
                + customer.getBroncoID() + ", \'" + customer.getName() + "\', \'" + customer.getPassword() + "\', "
                + customer.getPhoneNumber()
                + ", \'" + customer.getDOB() + "\', " + customer.getDiscountScheme() + ", " + customer.getStatus()
                + ");";
        String insertAddresssql = "INSERT INTO public.\"Address\"(\"broncoID\", street, \"zipCode\", city, state) VALUES ("
                + customer.getBroncoID() + ",\'" + customer.getStreet() + "\'," + customer.getZipCode()
                + ", \'" + customer.getCity() + "\', \'" + customer.getState() + "\');";

        try {
            Statement stmnt = conn.createStatement();
            stmnt.executeUpdate(insertCustomersql);
            stmnt.executeUpdate(insertAddresssql);
            System.out.println("Customer " + customer.getName() + " added successfully!");
            return true;

        } catch (SQLException e) {
            System.out.println("SQL Error");
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public static boolean deleteCustomer(Customer customer) {
        conn = DBConnect.startConnection();

        // DELETE FROM CUSTOMERS WHERE broncoID = customer.getBronoID()
        String deleteCustomersql = "DELETE FROM public.\"CUSTOMERS\" WHERE \"broncoID\" = "
                + customer.getBroncoID() + ";";
        String deleteAddresssql = "DELETE FROM public.\"Address\" WHERE \"broncoID\" = "
                + customer.getBroncoID() + ";";

        try {
            Statement stmnt = conn.createStatement();
            stmnt.executeUpdate(deleteCustomersql);
            System.out.println("Customer deleted successfully!");

            stmnt.executeUpdate(deleteAddresssql);
            System.out.println("Address deleted successfully!");
            return true;
        } catch (SQLException e) {
            System.out.println("SQL Error");
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
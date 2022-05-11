package model.classes;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;

public class Activity {
    private int productID;
    private String name;
    private float price;
    private String date;

    private Button button;
    private DatePicker datePicker;

    public Activity(int productID, String name, float price) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.date = "2022-02-22";
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getID(){
        return this.productID;
    }

    public void setID(int ID){
        this.productID = ID;
    }

    public float getPrice(){
        return price;
    }

    public String getDate(){
        return date;
    }

}

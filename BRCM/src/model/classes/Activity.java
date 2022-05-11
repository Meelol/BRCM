package model.classes;

import java.time.LocalDate;
import java.time.Month;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;

public class Activity {
    private int productID;
    private String name;
    private float price;
    private String date;

    private Button button;

    public Activity(int productID, String name, float price, String date, Button button) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.date = date;
        this.button = button;
        setButtonText(date);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return this.productID;
    }

    public void setID(int ID) {
        this.productID = ID;
    }

    public float getPrice() {
        return price;
    }

    public String getDate() {
        return date;
    }

    public void setButtonText(String text){
        this.button.setText(text);
    }
    public Button getButton(){
        return this.button;
    }

}

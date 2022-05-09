package model.classes;

import java.time.LocalDate;
import java.time.Month;

public class Customer {
    public static enum Status {
        STUDENT, PROFESSOR, BOTH
    }

    private int broncoID;
    private String password;
    private String name;
    private String phoneNumber;
    private Address address;
    private String DOB;
    private float discountScheme = (float) 0.0;
    private Status status = null;

    public Customer(int broncoID, String password, String name, String phoneNumber,
            String street, String city, String state, int zipCode,
            String DOB) {
        this.broncoID = broncoID;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = new Address(street, city, state, zipCode);
        this.DOB = DOB;
    }

    public void setDiscountScheme(Status status) {
        if (status == Status.BOTH) {
            this.discountScheme = (float) 0.25;
        } else if (status == Status.STUDENT) {
            this.discountScheme = (float) 0.20;
        } else if (status == Status.PROFESSOR) {
            this.discountScheme = (float) 0.15;
        }
    }

    public float getDiscountScheme() {
        return this.discountScheme;
    }

    public int getStatus(){
        return this.status.ordinal();
    }

    public int getBroncoID() {
        return this.broncoID;
    }

    public String getPassword(){
        return this.password;
    }
    public String getName(){
        return this.name;
    }
    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    public String getDOB(){
        return this.DOB;
    }
    public String getStreet(){
        return address.getStreet();
    }
    public String getCity(){
        return address.getCity();
    }
    public String getState(){
        return address.getState();
    }
    public int getZipCode(){
        return address.getZipCode();
    }
    public void setStatus(Status s){
        this.status = s;
    }


}
package model.classes;

import java.time.LocalDate;
import java.time.Month;

public class Customer {
    protected enum Status {
        STUDENT, PROFESSOR, BOTH
    }

    private int broncoID;
    private String password;
    private String name;
    private String phoneNumber;
    private Address address;
    private LocalDate DOB;
    private float discountScheme = (float) 0.0;
    private Status status = null;

    public Customer(int broncoID, String password, String name, String phoneNumber,
            String street, String city, String state, int zipCode,
            int year, Month month, int day) {
        this.broncoID = broncoID;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = new Address(street, city, state, zipCode);
        this.DOB = LocalDate.of(year, month, day);
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

    public int getBroncoID() {
        return this.broncoID;
    }
}
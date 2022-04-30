package BRCM_classes;
public class Address {
    private String street;
    private int unitNumber;
    private String city;
    private String state;
    private int zipCode;

    public Address(String street, int unitNumber, String city, String state, int zipCode) {
        this.street = street;
        this.unitNumber = unitNumber;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }
}

package classes;
import java.time.Month;

public class Professor extends Customer {
    private String office;
    private String department;
    private String research;
    private Status status = Status.PROFESSOR;

    public Professor(int broncoID, String name, String phoneNumber, String street,
            int unitNumber, String city, String state, int zipCode,
            int year, Month month, int day, String office, String department, String research) {
        super(broncoID, name, phoneNumber, street, unitNumber, city, state, zipCode, year, month, day);
        this.office = office;
        this.department = department;
        this.research = research;
        this.setDiscountScheme(this.status);
    }
}

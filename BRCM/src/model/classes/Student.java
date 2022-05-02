package model.classes;
import java.time.LocalDate;
import java.time.Month;

public class Student extends Customer {
    private String major;
    private String minor;
    private LocalDate enterDate;
    private LocalDate gradDate;
    private Status status = Status.STUDENT;

    public Student(int broncoID, String name, String phoneNumber, String street,
            int unitNumber, String city, String state, int zipCode,
            int year, Month month, int day, String major, int enterYear, Month enterMonth, int enterDay,
            int gradYear, Month gradMonth, int gradDay) {
        super(broncoID, name, phoneNumber, street, unitNumber, city, state, zipCode, year, month, day);
        this.major = major;
        this.enterDate = LocalDate.of(enterYear, enterMonth, enterDay);
        this.gradDate = LocalDate.of(gradYear, gradMonth, gradDay);
        this.setDiscountScheme(this.status);
    }

    public String printDiscountType(){
        return "STUDENT";
    }

    public void setMinor(String minor) {
        this.minor = minor;
    }
}

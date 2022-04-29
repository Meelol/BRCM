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
            int year, Month month, int day, String major, String minor,
            int enterYear, Month enterMonth, int enterDay, int gradDay, Month gradMonth, int gradYear) {
        super(broncoID, name, phoneNumber, street, unitNumber, city, state, zipCode, year, month, day);
        this.major = major;
        this.minor = minor;
        this.enterDate = LocalDate.of(enterYear, enterMonth, enterDay);
        this.gradDate = LocalDate.of(gradYear, gradMonth, gradDay);
        this.setDiscountScheme(this.status);
    }
}

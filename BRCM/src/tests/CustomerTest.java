package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.SignUpModel;
import model.classes.Customer;

public class CustomerTest {
    private Customer studentCustomer;
    private Customer professorCustomer;
    private Customer bothCustomer;
    private float s = 0;
    private float p = 0;
    private float b = 0;

    @Before
    public void setUp() {
        studentCustomer = new Customer(12341, "password", "test", "1234567890", "1234 Pomona Dr", "Pomona",
                "California", 12345, "1911-11-11");
        studentCustomer.setStatus(Customer.Status.STUDENT);
        studentCustomer.setDiscountScheme(Customer.Status.STUDENT);
        s = studentCustomer.getDiscountScheme();
        SignUpModel.addCustomer(studentCustomer);

        professorCustomer = new Customer(567812, "password", "professor", "1234567890", "1234 Pomona Dr", "Pomona",
                "California", 12345, "1911-11-11");
        professorCustomer.setStatus(Customer.Status.PROFESSOR);
        professorCustomer.setDiscountScheme(Customer.Status.PROFESSOR);
        p = professorCustomer.getDiscountScheme();
        SignUpModel.addCustomer(professorCustomer);

        bothCustomer = new Customer(9090, "password", "both", "1234567890", "1234 Pomona Dr", "Pomona",
                "California", 12345, "1911-11-11");
        bothCustomer.setStatus(Customer.Status.BOTH);
        bothCustomer.setDiscountScheme(Customer.Status.BOTH);
        b = bothCustomer.getDiscountScheme();
        SignUpModel.addCustomer(bothCustomer);
    }

    // test to see if getDiscountScheme returns correct discount for students
    @Test
    public void studentDiscountTest() {
        assertEquals((float) 0.20, s, 0.0001);
    }

    // test to see if getDiscountScheme returns correct discount for professors
    @Test
    public void professorDiscountTest() {
        assertEquals((float) 0.15, p, 0.0001);
    }

    // test to see if getDiscountScheme returns correct discount for professors
    @Test
    public void bothDiscountTest() {
        assertEquals((float) 0.25, b, 0.0001);
    }

    @After
    public void tearDown() {
        assertTrue(SignUpModel.deleteCustomer(studentCustomer));
        assertTrue(SignUpModel.deleteCustomer(professorCustomer));
        assertTrue(SignUpModel.deleteCustomer(bothCustomer));
    }

}

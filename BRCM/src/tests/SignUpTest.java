package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.SignUpModel;
import model.classes.Customer;

public class SignUpTest {
    private Customer validCustomer;
    private Customer invalidCustomer;

    @Before
    public void setUp() {
        validCustomer = new Customer(1234, "password", "testname", "1234567890", "1234 Pomona Dr", "Pomona",
                "California", 12345, "1911-11-11");
        validCustomer.setStatus(Customer.Status.BOTH);
    }

    // test to see if valid customer is successfully added
    @Test
    public void addCustomer() {
        assertTrue(SignUpModel.addCustomer(validCustomer));
    }



    @After
    public void tearDown() {
        assertTrue(SignUpModel.deleteCustomer(validCustomer));
    }
}

package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.LoginModel;
import model.SignUpModel;
import model.classes.Customer;

public class LoginTest {

    private Customer validCustomer;
    private Customer studentCustomer;

    @Before
    public void setUp() {
        validCustomer = new Customer(1234, "password", "testname", "1234567890", "1234 Pomona Dr", "Pomona",
                "California", 12345, "1911-11-11");
        validCustomer.setStatus(Customer.Status.STAFF);
        SignUpModel.addCustomer(validCustomer);

        studentCustomer = new Customer(9999, "password123", "testname", "1234567890", "1234 Pomona Dr", "Pomona",
                "California", 12345, "1911-11-11");
        studentCustomer.setStatus(Customer.Status.STUDENT);
        SignUpModel.addCustomer(studentCustomer);
    }

    // Test for LoginModel checkCredentials for customer whose credentials are in
    // database
    @Test
    public void checkCredentials() {
        assertTrue(LoginModel.checkCredentials("" + validCustomer.getBroncoID(), validCustomer.getPassword()));
    }

    // Test for checkCredentials for customer whose credentials are NOT in the
    // database
    @Test
    public void invalidCheckCredentials() {
        assertFalse(LoginModel.checkCredentials("9999", "thispasswordshouldneverbeinthedatabase"));
    }

    // Test isStaff with customer who is STAFF
    @Test
    public void isStaff() {
        assertTrue(LoginModel.isStaff("" + validCustomer.getBroncoID(), validCustomer.getPassword()));
    }

    // Test isStaff with customer who is STUDENT or PROFESSOR
    @Test
    public void isNotStaff() {
        assertFalse(LoginModel.isStaff("" + studentCustomer.getBroncoID(), studentCustomer.getPassword()));
    }

    @After
    public void tearDown() {
        SignUpModel.deleteCustomer(validCustomer);
        SignUpModel.deleteCustomer(studentCustomer);
    }
}

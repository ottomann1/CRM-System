package test;

import Backend.BusinessObjects.Customer;
import Backend.DAO.CustomerDao;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Calendar;

public class test {
    Customer customer = new Customer("Olle", "Svensson");


    @Test
    public void test() {
        Calendar.getInstance().getTime();
        System.out.println(Calendar.getInstance().getTime());
    }

    @Test
    public void testToString() {
        System.out.println(customer.toString());
    }

    @Test
    public void testCustomerSerializable() throws IOException, ClassNotFoundException {
        CustomerDao customerDao = new CustomerDao();
//        System.out.println(customerDao.toString());
    }
}

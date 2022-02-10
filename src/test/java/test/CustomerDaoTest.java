package test;

import Backend.BusinessObjects.Customer;
import Backend.DAO.CustomerDao;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerDaoTest {
    CustomerDao customerDao = new CustomerDao();

    public CustomerDaoTest() throws IOException, ClassNotFoundException {
    }

    @Test
    public void testDelete() throws IOException, ClassNotFoundException {
        if(!customerDao.getAll().isEmpty()) {
            for (int i = customerDao.getAll().size(); i > 0; i--) {
                customerDao.delete(customerDao.get(i-1).get());
            }
        }
        assertEquals(customerDao.getAll().isEmpty(), true);
    }

    @Test
    public void testReadAndWrite() throws IOException, ClassNotFoundException {
        Customer customer = new Customer("Yeees", "I Like This");
        customerDao.save(customer);
        Customer customer2 = customerDao.get(customer.getId()-1).get();
        assertEquals(customer.toString(), customer2.toString());
    }

}

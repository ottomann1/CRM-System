package test;

import Backend.BusinessObjects.Customer;
import Backend.BusinessObjects.Representative;
import Backend.BusinessObjects.Sale;
import Backend.DAO.CustomerDao;
import Backend.DAO.RepresentativeDao;
import Backend.DAO.SaleDao;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SaleTest {
    CustomerDao customerDao = new CustomerDao();
    RepresentativeDao representativeDao = new RepresentativeDao();
    SaleDao saleDao = new SaleDao();

    public SaleTest() throws IOException, ClassNotFoundException {
        customerDao.deleteAll();
        representativeDao.deleteAll();
        saleDao.deleteAll();
    customerDao.save(new Customer("Pelle", "Pungpinan"));
    representativeDao.save(new Representative("Marre", "Frunkan"));
    saleDao.save(new Sale("Heroin", 5, customerDao.get(0).get(), representativeDao.get(0).get()));
    }
    @Test
    public void testSale() throws IOException, ClassNotFoundException {
        System.out.println(saleDao.getAll().toString());
        System.out.println(customerDao.getAll().toString());
        assertEquals(5,5);
    }


}

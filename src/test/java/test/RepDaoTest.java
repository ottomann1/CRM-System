package test;

import Backend.BusinessObjects.Representative;
import Backend.DAO.RepresentativeDao;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepDaoTest {
    RepresentativeDao RepresentativeDao = new RepresentativeDao();

    public RepDaoTest() throws IOException, ClassNotFoundException {
    }

    @Test
    public void testDelete() throws IOException, ClassNotFoundException {
            for (int i = RepresentativeDao.getAll().size(); i > 0; i--) {
                RepresentativeDao.delete(RepresentativeDao.get(i-1).get());
            }
        assertEquals(RepresentativeDao.getAll().isEmpty(), true);
    }

    @Test
    public void testReadAndWrite() throws IOException, ClassNotFoundException {
        Representative Representative = new Representative("Yeees", "I Like This");
        RepresentativeDao.save(Representative);
        Representative Representative2 = RepresentativeDao.get(Representative.getId()).get();
        assertEquals(Representative.toString(), Representative2.toString());
    }

     @Test
    public void testPrintAllReps() throws IOException, ClassNotFoundException {

         System.out.println(RepresentativeDao.getAll().toString());
     }
}

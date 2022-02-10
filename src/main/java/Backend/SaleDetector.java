package Backend;

import Backend.BusinessObjects.Representative;
import Backend.BusinessObjects.Sale;
import Backend.DAO.RepresentativeDao;
import Backend.DAO.SaleDao;

import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class SaleDetector {
    public void detectReps(Sale sale) throws IOException, ClassNotFoundException {
        long customerId = sale.getBuyerId();
        SaleDao saleDao = new SaleDao();
        RepresentativeDao representativeDao = new RepresentativeDao();
        List<String> writtenreps = new LinkedList<String>();
        for (int i = 0; i < saleDao.getAll().size(); i++) {
            if(saleDao.get(i).get().getBuyerId() == customerId && saleDao.get(i).get().getSellerId() != sale.getSellerId()){
                Representative rep = representativeDao.get(saleDao.get(i).get().getSellerId()-1).get();
                Representative rep2 = rep;
                if(!(writtenreps.contains(rep.toString()))){
                    rep2.addToActivity("[" + Calendar.getInstance().getTime() + "]" + "Försäljning av " +sale.getAmount()+" x "+sale.getProduct()+" från " + sale.getSeller() + " till " + sale.getBuyer());
                    representativeDao.update(rep, rep2);
                    writtenreps.add(rep2.toString());
                }
            }
        }
    }
}

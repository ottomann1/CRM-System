package Backend;

import Backend.BusinessObjects.Customer;
import Backend.BusinessObjects.Representative;
import Backend.BusinessObjects.Sale;
import Backend.DAO.SaleDao;

import java.io.IOException;

public class Chartmaker {
    public Chartmaker(){

    }
    public String customerChart(Customer customer) throws IOException, ClassNotFoundException {
        SaleDao saleDao = new SaleDao();
        String chartString="";
        chartString+="           ***"+customer.getName()+"***\n";
        chartString+=" ______________________________________________________\n";
        chartString+="           Date/Time         |Product x Amount - Seller\n";
        chartString+=" ______________________________________________________\n";
        for (int i = 0; i < saleDao.getAll().size(); i++) {
            if (saleDao.get(i).get().getBuyerId() == customer.getId()) {
                chartString+=""+saleDao.get(i).get().getTimeOfSale()+" | "+saleDao.get(i).get().getProduct()+"  x  "+saleDao.get(i).get().getAmount()+" - "+saleDao.get(i).get().getSeller()+"\n";
                chartString+=" ______________________________________________________\n";
            }
        }
        return chartString;
    }

    public String representativeChart(Representative rep) throws IOException, ClassNotFoundException {
        SaleDao saleDao = new SaleDao();
        String chartString="";
        chartString+="               ***"+rep.getName()+"***\n";
        chartString+=" _____________________________________________________\n";
        chartString+="           Date/Time         |Product x Amount - Buyer\n";
        chartString+=" _____________________________________________________\n";
        for (int i = 0; i < saleDao.getAll().size(); i++) {
            if (saleDao.get(i).get().getSellerId() == rep.getId()) {
                chartString+=""+saleDao.get(i).get().getTimeOfSale()+" | "+saleDao.get(i).get().getProduct()+"  x  "+saleDao.get(i).get().getAmount()+" - "+saleDao.get(i).get().getBuyer()+"\n";
                chartString+=" ______________________________________________________\n";
            }
        }
        return chartString;
    }

    public String saleChart(String product) throws IOException, ClassNotFoundException {
        SaleDao saleDao = new SaleDao();
        String chartString="";
        chartString+="                    ***"+product+"***\n";
        chartString+=" ____________________________________________________________\n";
        chartString+="           Date/Time         |Product x Amount - Buyer/Seller\n";
        chartString+=" ____________________________________________________________\n";
        for (Sale sale:saleDao.getAll()){
            if(sale.getProduct().equals(product)) {
                chartString += "" + sale.getTimeOfSale() + " | " + sale.getProduct() + "  x  " + sale.getAmount() + " - " + sale.getBuyer() + "/" + sale.getSeller() + "\n";
                chartString += " ____________________________________________________________\n";
            }
            }
        return chartString;
    }

}

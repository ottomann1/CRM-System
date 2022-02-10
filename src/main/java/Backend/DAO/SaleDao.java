package Backend.DAO;

import Backend.BusinessObjects.Sale;
import Backend.Database.CustomerHistorySerializable;
import Backend.Database.SaleHistorySerializable;
import Backend.SaleDetector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SaleDao implements Dao<Sale> {

    private List<Sale> sales = new ArrayList<>();
    private long currentId = 1;

    public SaleDao() throws IOException, ClassNotFoundException {
        sales = SaleHistorySerializable.getInstance().read();
        if(!(sales.isEmpty()))
        currentId = sales.size()+1;
    }

    @Override
    public Optional<Sale> get(long id) throws IOException, ClassNotFoundException {
        sales = SaleHistorySerializable.getInstance().read();
        return Optional.ofNullable(sales.get((int) id));
    }

    @Override
    public List<Sale> getAll() throws IOException, ClassNotFoundException {
        sales = SaleHistorySerializable.getInstance().read();
        return sales;
    }

    @Override
    public void save(Sale sale) throws IOException, ClassNotFoundException {
        sale.setId(currentId++);
        sales.add(sale);
        SaleHistorySerializable.getInstance().write(sales);
    }

    @Override
    public void update(Sale currentSale, Sale newSale) throws IOException {
        sales.set(sales.indexOf(currentSale), newSale);
        SaleHistorySerializable.getInstance().write(sales);
    }

    @Override
    public void delete(Sale sale) throws IOException {
        sales.remove(sale);
        SaleHistorySerializable.getInstance().write(sales);
    }

    @Override
    public void deleteAll() throws IOException {
        for (int i = sales.size()-1; i >= 0; i--) {
            sales.remove(i);
        }
        currentId=0;
        SaleHistorySerializable.getInstance().write(sales);
    }
}
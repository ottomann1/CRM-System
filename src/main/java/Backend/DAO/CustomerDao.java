package Backend.DAO;

import Backend.BusinessObjects.Customer;
import Backend.Database.CustomerHistorySerializable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerDao implements Dao<Customer> {

    private List<Customer> customers = new ArrayList<>();
    private long currentId = 1;

    public CustomerDao() throws IOException, ClassNotFoundException {
        customers = CustomerHistorySerializable.getInstance().read();
        if(!(customers.isEmpty()))
        currentId = customers.size()+1;
    }

    @Override
    public Optional<Customer> get(long id) throws IOException, ClassNotFoundException {
        customers = CustomerHistorySerializable.getInstance().read();
        return Optional.ofNullable(customers.get((int) id));
    }

    @Override
    public List<Customer> getAll() throws IOException, ClassNotFoundException {
        customers = CustomerHistorySerializable.getInstance().read();
        return customers;
    }

    @Override
    public void save(Customer customer) throws IOException, ClassNotFoundException {
        customer.setId(currentId++);
        System.out.println(customer.getId());
        customers.add(customer);
        CustomerHistorySerializable.getInstance().write(customers);
    }

    @Override
    public void update(Customer customer, Customer newCustomer) throws IOException {
        customers.set(customers.indexOf(customer), newCustomer);
        CustomerHistorySerializable.getInstance().write(customers);
    }

    @Override
    public void delete(Customer customer) throws IOException {
        customers.remove(customer);
        CustomerHistorySerializable.getInstance().write(customers);
    }

    @Override
    public void deleteAll() throws IOException {
        for (int i = customers.size()-1; i >= 0 ; i--) {
            customers.remove(i);
        }
        currentId=0;
        CustomerHistorySerializable.getInstance().write(customers);
    }
}

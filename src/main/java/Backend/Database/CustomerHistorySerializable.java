package Backend.Database;

import Backend.BusinessObjects.Customer;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CustomerHistorySerializable {
    private static final CustomerHistorySerializable eager = new CustomerHistorySerializable();
    private final SerializableFileHandler fileHandler;

    private CustomerHistorySerializable() {
        fileHandler = new SerializableFileHandler(new File("Customers.txt"));
    }

    public static CustomerHistorySerializable getInstance() {
        return eager;
    }

    public List<Customer> read() throws IOException, ClassNotFoundException {
        return (List<Customer>) fileHandler.readObject();
    }

    public void write(List<Customer> customerDao) throws IOException {
        fileHandler.writeObject(customerDao);
    }
}

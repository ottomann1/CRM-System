package Backend.Database;

import Backend.BusinessObjects.Sale;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SaleHistorySerializable {
    private static final SaleHistorySerializable eager = new SaleHistorySerializable();
    private final SerializableFileHandler fileHandler;

    private SaleHistorySerializable() {
        fileHandler = new SerializableFileHandler(new File("Sales.txt"));
    }

    public static SaleHistorySerializable getInstance() {
        return eager;
    }

    public List<Sale> read() throws IOException, ClassNotFoundException {
        return (List<Sale>) fileHandler.readObject();
    }

    public void write(List<Sale> SaleDao) throws IOException {
        fileHandler.writeObject(SaleDao);
    }
}

package Backend.Database.Old;

import Backend.Database.FileHandler;

import java.io.File;
import java.io.IOException;

public class CustomerHistory {
    private static CustomerHistory eager;

    static {
        try {
            eager = new CustomerHistory();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final File filename = new File("C:\\reporoot\\Database\\Customers.txt");
    private final String[] output;

    private CustomerHistory() throws IOException {
        FileHandler.getInstance().setFilepath(filename);
        output = FileHandler.getInstance().read();
    }

    public static CustomerHistory getInstance() {
        return eager;
    }

    public String[] read() {
        return output;
    }

    public void save(String[] input) throws IOException {
        FileHandler.getInstance().save(input);
    }

}

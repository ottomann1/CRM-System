package Backend.Database;

import Backend.BusinessObjects.Representative;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class RepresentativeHistorySerializable {
    private static final RepresentativeHistorySerializable eager = new RepresentativeHistorySerializable();
    private final SerializableFileHandler fileHandler;

    private RepresentativeHistorySerializable() {
        fileHandler = new SerializableFileHandler(new File("Representatives.txt"));
    }

    public static RepresentativeHistorySerializable getInstance() {
        return eager;
    }

    public List<Representative> read() throws IOException, ClassNotFoundException {
        return (List<Representative>) fileHandler.readObject();
    }

    public void write(List<Representative> representativeDao) throws IOException {
        fileHandler.writeObject(representativeDao);
    }
}

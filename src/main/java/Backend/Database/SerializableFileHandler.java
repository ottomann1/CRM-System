package Backend.Database;

import Backend.BusinessObjects.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializableFileHandler {

    private final File file;

    public SerializableFileHandler(File filepath) {
        file = filepath;
    }

    public final void writeObject(Object object) throws IOException {
        FileOutputStream fileOutputStream
                = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream
                = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    public final Object readObject() throws IOException, ClassNotFoundException {
        if(!file.exists()){
            List<Person> person = new ArrayList<>();
            writeObject(person);
        }
        FileInputStream fileInputStream
                = new FileInputStream(file);
        ObjectInputStream objectInputStream
                = new ObjectInputStream(fileInputStream);
        Object object = objectInputStream.readObject();
        objectInputStream.close();
        return object;
    }
}

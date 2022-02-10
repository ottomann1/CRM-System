package Backend.DAO;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> get(long id) throws IOException, ClassNotFoundException;

    List<T> getAll() throws IOException, ClassNotFoundException;

    void save(T t) throws IOException, ClassNotFoundException;

    void update(T currentT, T newT) throws IOException;

    void delete(T t) throws IOException;

    void deleteAll() throws IOException;
}

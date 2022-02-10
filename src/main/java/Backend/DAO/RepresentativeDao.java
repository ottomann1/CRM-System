package Backend.DAO;

import Backend.BusinessObjects.Representative;
import Backend.Database.RepresentativeHistorySerializable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepresentativeDao implements Dao<Representative> {

    private List<Representative> representatives = new ArrayList<>();
    private long currentId = 1;

    public RepresentativeDao() throws IOException, ClassNotFoundException {
        representatives = RepresentativeHistorySerializable.getInstance().read();
        if(!(representatives.isEmpty()))
        currentId = representatives.size()+1;
    }

    @Override
    public Optional<Representative> get(long id) throws IOException, ClassNotFoundException {
        representatives = RepresentativeHistorySerializable.getInstance().read();
        return Optional.ofNullable(representatives.get((int) id));
    }

    @Override
    public List<Representative> getAll() throws IOException, ClassNotFoundException {
        representatives = RepresentativeHistorySerializable.getInstance().read();
        return representatives;
    }

    private Representative temprep;

    @Override
    public void save(Representative representative) throws IOException {
        representative.setId(currentId++);
        representatives.add(representative);
        RepresentativeHistorySerializable.getInstance().write(representatives);
        temprep = representative;
    }

    public Representative getLastRep(){
        return temprep;
    }

    @Override
    public void update(Representative currentRepresentative, Representative newRepresentative) throws IOException {
        representatives.set(representatives.indexOf(currentRepresentative), newRepresentative);
        RepresentativeHistorySerializable.getInstance().write(representatives);
    }

    @Override
    public void delete(Representative representative) throws IOException {
        representatives.remove(representative);
        RepresentativeHistorySerializable.getInstance().write(representatives);
    }

    @Override
    public void deleteAll() throws IOException {
        for (int i = representatives.size()-1; i >= 0; i--) {
            representatives.remove(i);
        }
        currentId=0;
        RepresentativeHistorySerializable.getInstance().write(representatives);
    }
}

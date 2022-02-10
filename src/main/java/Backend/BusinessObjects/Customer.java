package Backend.BusinessObjects;

import java.io.Serializable;
import java.util.Calendar;

public class Customer extends Person implements Serializable {
    private static final long serialVersionUID = 1L;
    public Customer(String name, String address) {
        setName(name);
        setAddress(address);
        setTimeOfCreation(Calendar.getInstance().getTime());
    }

    @Override
    public String toString() {
        return "{ID: "+super.getId() +", Name: "+super.getName()+"}";
    }
}

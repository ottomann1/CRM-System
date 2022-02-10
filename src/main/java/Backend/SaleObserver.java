package Backend;

import Backend.BusinessObjects.Sale;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class SaleObserver implements PropertyChangeListener {

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getOldValue().equals(true)){
            SaleDetector saleDetector = new SaleDetector();
            try {
                saleDetector.detectReps((Sale) evt.getSource());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else {

        }
    }
}

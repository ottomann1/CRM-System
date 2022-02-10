package Backend.BusinessObjects;

import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Representative extends Person implements Serializable {
    private static final long serialVersionUID = 1L;
    List<String> activityList = new ArrayList<>();
    List<Sale> salesList = new ArrayList<>();


    public Representative(String name, String address){
        setName(name);
        setAddress(address);
        setTimeOfCreation(Calendar.getInstance().getTime());
    }

    public List<Sale> getSalesList() {
        return salesList;
    }

    public void setSalesList(List<Sale> salesList) {
        this.salesList = salesList;
    }

    public void addToSalesList(Sale sale){
        salesList.add(sale);
    }

    public List<String> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<String> activityList) {
        this.activityList = activityList;
    }

    public void addToActivity(String activity){
        activityList.add(activity);
    }

    public void addToActivityProper(){
        activityList.add(Calendar.getInstance().getTime()+"Försäljning");
    }

    public void removeActivity(int index){
        activityList.remove(index);
    }

    public void removeAllActivities(){
        for (int i = activityList.size()-1; i >= 0; i--) {
            activityList.remove(i);
        }

    }

    @Override
    public String toString() {
        return "{ID: "+super.getId() +", Name: "+super.getName()+"}";
    }
}

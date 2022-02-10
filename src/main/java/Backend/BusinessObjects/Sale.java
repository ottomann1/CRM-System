package Backend.BusinessObjects;

import Backend.SaleDetector;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Sale implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private String product;
    private int amount;
    private Date timeOfSale; //use calendar
    private long sellerId; //(String name+id)
    private long buyerId; //(String name+id)
    private String seller;
    private String buyer;
    private PropertyChangeSupport propertyChangeSupport;
    private boolean newSale;

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public Sale(String product, int amount, Customer customer, Representative rep) throws IOException, ClassNotFoundException {
        this.product = product;
        this.amount = amount;
        buyerId =customer.getId();
        sellerId=rep.getId();
        timeOfSale=Calendar.getInstance().getTime();
        seller=rep.getName();
        buyer=customer.getName();
        newSale=true;
        this.propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener){
        this.propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void newSale(){
        boolean oldNewSale = newSale;
        newSale = false;
        propertyChangeSupport.firePropertyChange("New Sale", oldNewSale, newSale);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getTimeOfSale() {
        return timeOfSale;
    }

    public void setTimeOfSale(Date timeOfSale) {
        this.timeOfSale = timeOfSale;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerid) {
        this.sellerId = sellerid;
    }

    public long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(long buyerId) {
        this.buyerId = buyerId;
    }

    @Override
    public String toString() {
        return "[" +timeOfSale+
                "] Product: " + product +
                ", Sale ID: " + id +
                ", Amount: " + amount +
                ", Seller: " + seller +
                ", Buyer: " + buyer;
    }
}

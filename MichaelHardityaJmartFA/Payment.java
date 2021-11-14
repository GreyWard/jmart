package MichaelHardityaJmartFA;

import java.util.ArrayList;
import java.util.Date;

public class Payment extends Invoice
{
    public ArrayList<Record> history;
    public Shipment shipment;
    public int productCount;
    public Payment(int buyerId,int productId,int productCount, Shipment shipment)
    {
        super(buyerId, productId);
        this.productId = productId;
        this.productCount = productCount;
        this.shipment = shipment;
    }
    class Record{
        public Date date;
        public String message;
        public Status status;
        public Record(Status status, String message) {
        	this.date = new Date();
        	this.message = message;
        	this.status = status;
            history = new ArrayList<Record>();
        }
    }
    public double getTotalPay(Product product)
    {
		return Treasury.getAdjustedPrice(product.price, product.discount);
    }
    public boolean validate()
    {
        return false;
    }
    public Invoice perform()
    {
        return null;
    }
}

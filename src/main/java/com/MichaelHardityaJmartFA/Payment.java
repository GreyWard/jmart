package com.MichaelHardityaJmartFA;

import java.util.ArrayList;
import java.util.Date;
/**
 * Class to track Payment information of a purchase until recieved
 * @see Invoice
 * @see Record
 * @see Shipment
 * @author Michael Harditya
 *
 */
public class Payment extends Invoice
{
    public ArrayList<Record> history= new ArrayList<Record>();
    public Shipment shipment;
    public int productCount;
    public String productName;
    /**
     * Records the Payment activities information
     * @param buyerId An Account ID of the purchaser
     * @param productId A Product ID of the purchased product
     * @param productCount Purchased product quantity
     * @param shipment shipment method of the product
     */
    public Payment(int buyerId,int productId,int productCount, Shipment shipment,String productName)
    {
        super(buyerId, productId);
        this.productCount = productCount;
        this.shipment = shipment;
        this.productName = productName;
        this.status = history.get(history.size()-1).status;
    }
    /**
     * Class to Record the date, message, and status of the purchase and status changes
     * @author Michael Harditya
     *
     */
    public class Record{
        public Date date;
        public String message;
        public Status status;
        /**
         * Records date, message, and status of the purchase or status changes
         * @param status the status of the record
         * @param message the message of the record
         */
        public Record(Status status, String message) {
        	this.date = new Date();
        	this.message = message;
        	this.status = status;
        }
    }
    /**
     * Check the total price of the product by multiply it with product quantity
     * 
     */
    public double getTotalPay(Product product)
    {
		return Treasury.getAdjustedPrice(product.price, product.discount)*productCount;
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

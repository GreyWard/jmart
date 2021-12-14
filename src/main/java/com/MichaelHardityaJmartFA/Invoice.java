package com.MichaelHardityaJmartFA;
import java.util.Date;

import com.MichaelHardityaJmartFA.dbjson.Serializable;
/**
 * Class to Record Payment and status of a purchase
 * 
 * @author Michael Harditya
 */
public abstract class Invoice extends Serializable
{
    enum Rating{
        NONE,
        BAD,
        NEUTRAL,
        GOOD};
    public enum Status{
        WAITING_CONFIRMATION,
        CANCELLED,
        ON_PROGRESS,
        ON_DELIVERY,
        DELIVERED,
        COMPLAINT,
        FINISHED,
        FAILED};
    public final Date date;
    public int buyerId;
    public int productId;
    public int complaintId;
    public Rating rating;
    public Status status;
    /**
     * Initiate a new Invoice
     * @param buyerId An Account ID of the purchaser
     * @param productId A Product ID purchased
     */
    
    public Invoice(int buyerId, int productId)
    {
        this.buyerId = buyerId;
        this.productId = productId;
        this.rating = Rating.NONE;
        this.complaintId = -1;
        this.status = Status.WAITING_CONFIRMATION;
        this.date = new Date();
    }
    public boolean read(String content)
    {
        return false;
    }
    public abstract double getTotalPay(Product product);
}

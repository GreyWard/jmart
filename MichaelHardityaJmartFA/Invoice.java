package MichaelHardityaJmartFA;
import java.util.Date;
/**
 * Write a description of class Invoice here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class Invoice extends Serializable
{
    enum Rating{
        NONE,
        BAD,
        NEUTRAL,
        GOOD};
    enum Status{
        WAITING_CONFIRMATION,
        CANCELLED,
        ON_PROGRESS,
        ON_DELIVERY,
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
     * Constructor for objects of class Invoice
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

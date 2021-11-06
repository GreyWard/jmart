package MichaelHardityaJmartFA;
import java.util.Date;
import java.util.ArrayList;
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
    public ArrayList<Record> history;
    /**
     * Constructor for objects of class Invoice
     */
    class Record{
        public Date date;
        public String message;
        public Status status;
    }
    public Invoice(int buyerId, int productId)
    {
        this.buyerId = buyerId;
        this.productId = productId;
        this.rating = Rating.NONE;
        this.status = Status.WAITING_CONFIRMATION;
        this.date = new Date();
        this.history = new ArrayList<Record>();
    }
    public boolean read(String content)
    {
        return false;
    }
    public abstract double getTotalPay();
}

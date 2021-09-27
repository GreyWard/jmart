package MichaelHardityaJmartFA;


/**
 * Write a description of class Invoice here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class Invoice extends Recognizable implements FileParser
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
    public String date;
    public int buyerId;
    public int productId;
    public int complaintId;
    public Rating rating;
    public Status status;
    /**
     * Constructor for objects of class Invoice
     */
    public Invoice(int id, int buyerId, int productId)
    {
        super(id);
        this.buyerId = buyerId;
        this.productId = productId;
        this.rating = Rating.NONE;
        this.status = Status.WAITING_CONFIRMATION;
        this.date = "27 Agustus 2021";
    }
    public boolean read(String content)
    {
        return false;
    }
    public abstract double getTotalPay();
}

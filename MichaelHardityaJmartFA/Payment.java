package MichaelHardityaJmartFA;


public class Payment extends Invoice
{
    public Shipment shipment;
    public int productCount;
    public Payment(int id,int buyerId,int productId,int productCount, Shipment shipment)
    {
        super(buyerId, productId);
        this.productId = productId;
        this.productCount = productCount;
        this.shipment = shipment;
    }
    public double getTotalPay()
    {
        return 0;
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

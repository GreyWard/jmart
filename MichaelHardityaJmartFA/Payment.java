package MichaelHardityaJmartFA;


public class Payment extends Transaction implements FileParser
{
    public int productId;
    public ShipmentDuration shipmentDuration;
    
    public Payment(int id,int buyerId,Product product,ShipmentDuration shipmentDuration)
    {
        super(id,buyerId,product.id);
        this.productId = product.id;
        this.shipmentDuration = shipmentDuration;
    }
    public Payment(int id,int buyerId,int storeId,int productId,ShipmentDuration shipmentDuration)
    {
        super(id,buyerId,productId);
        this.productId = productId;
        this.shipmentDuration = shipmentDuration;
    }
    public boolean validate()
    {
        return false;
    }
    public Transaction perform()
    {
        return null;
    }
    public boolean read(String content)
    {
        return false;
    }
}

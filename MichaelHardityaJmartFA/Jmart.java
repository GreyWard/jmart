package MichaelHardityaJmartFA;


/**
 * a JMart Apps for managing a store
 *
 * @author Michael Harditya
 * @version 0.0.1
 */
public class Jmart
{
    public static void main(String[] args){

    }
    public static Product createProduct(){
        PriceTag price = new PriceTag(10000,5);
        Product testcase = new Product("Primogem",1,true,price,ProductCategory.GAMING);
        return testcase;
    }
    public static Coupon createCoupun(){
        return null;
    }
    public static ShipmentDuration createShipmentDuration(){
        return null;
    }
}

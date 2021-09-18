package MichaelHardityaJmartFA;

public class PriceTag
{

    public static double COMMISSION_MULTIPLIER = 0.05;
    public static double BOTTOM_PRICE = 20000.0;
    public static double BOTTOM_FEE = 1000.0;
    public double discount;
    public double price;
    
    public PriceTag(double price){
       this.price = price;
       discount = 0.0;
    }

    public PriceTag(double price, double discount){
        this.price = price;
        this.discount = discount;
    }
    public double getAdjustedPrice(){
        return (getDiscountedPrice()+getAdminFee());
    }
    public double getAdminFee(){
        if (getDiscountedPrice()<this.BOTTOM_PRICE){
            return this.BOTTOM_FEE;
        }
        else{
            return (getDiscountedPrice()-getDiscountedPrice()*this.COMMISSION_MULTIPLIER);
        }
    }
    public double getDiscountedPrice(){
        if (this.discount > 100.0){
            return 0.0;
        }
        else if (this.discount == 100.0){
            return this.price;
        }
        else{
            return (this.price / 100 * (100 - this.discount));
        }
    }
}

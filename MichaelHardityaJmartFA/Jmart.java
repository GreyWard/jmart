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
    public static int getPromo(){
        return 0;
    }
    public static String getCustomer(){
        return "oop";
    }
    public static float getDiscountPercentage(int before, int after){
        float disc;
        if (before >= after){
        disc = ((float)(before - after)/before);
        }
        else{
            disc = 0;
        }
        return disc;
    }
    public static int getDiscountedPrice(int price, float discountPercentage){
        if (discountPercentage < 100 && discountPercentage > 0){
            price -= (price * discountPercentage);
	}
	else{
	    price = 0;
        }
	return price;
    }
    public static int getOriginalPrice(int discountedPrice, float discountPercentage){
        int price;
        discountPercentage = 100 - discountPercentage;
        price = discountedPrice + (int)(discountedPrice/discountPercentage);
        return price;
    }
    public static float getCommissionMultiplier(){
        return 0.05f;
    }
    public static int getAdjustedPrice(int price){
        return price + (int)(price * getCommissionMultiplier());
    }
    public static int getAdminFee(int price){
        return (int)(price * getCommissionMultiplier());
    }
}

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

    public int getPromo(){
        return 0;
    }
    public String getCustomer(){
        return "oop";
    }
    public float getDiscountPercentage(int before, int after){
        float disc;
        if (before >= after){
        disc = (before - after/100);
        }
        else{
            disc = 0;
        }
        return disc;
    }
}

package MichaelHardityaJmartFA;


/**
 * Write a description of class Coupon here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Coupon
{
    static public String name;
    static public int code;
    static public double cut;
    static public Type type;
    static public double minimum;
    private boolean used;
    public Coupon(String name, int code, Type type, double cut, double minimum)
    {
        this.name = name;
        this.code = code;
        this.type = type;
        this.cut = cut;
        this.minimum = minimum;
        this.used = false;
    }

    public boolean isUsed(){
        return used;
    }
    public boolean canApply(PriceTag pricetag){
        if ((pricetag.getAdjustedPrice()>=this.minimum)&&(this.used==false)){
            return true;
        }
        else{
            return false;
        }
    }
    public double apply(PriceTag pricetag){
        this.used = true;
        return (pricetag.getAdjustedPrice()-this.cut);
    }
}

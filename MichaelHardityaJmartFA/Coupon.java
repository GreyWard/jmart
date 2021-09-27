package MichaelHardityaJmartFA;

public class Coupon extends Recognizable implements FileParser
{
    public static enum Type{
        DISCOUNT,
        REBATE}
    public static String name;
    public static int code;
    public static double cut;
    public static Type type;
    public static double minimum;
    private boolean used;
    public Coupon(int id, String name, int code, Type type, double cut, double minimum)
    {
        super(id);
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
        if (this.type == Coupon.Type.DISCOUNT){
            return (pricetag.getAdjustedPrice()*(100-this.cut)/100);
        }
        else{
        return (pricetag.getAdjustedPrice()-this.cut);
    }
    }
    public boolean read(String content)
    {
        return false;
    }
}

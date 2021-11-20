package com.MichaelHardityaJmartFA;

import com.MichaelHardityaJmartFA.dbjson.Serializable;

public class Coupon extends Serializable
{
    public static enum Type{
        DISCOUNT,
        REBATE}
    public final int code;
    public final double cut;
    public final double minimum;
    public final String name;
    public final Type type;
    private boolean used;
    public Coupon (String name, int code, Type type, double cut, double minimum)
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
    public boolean canApply(double price, double discount){
        if ((Treasury.getAdjustedPrice(price,discount)>=this.minimum)&&(this.used==false)){
            return true;
        }
        else{
            return false;
        }
    }
    public double apply(double price, double discount){
        this.used = true;
        if (this.type == Coupon.Type.DISCOUNT){
            return (Treasury.getAdjustedPrice(price,discount)*(100-this.cut)/100);
        }
        else{
        return (Treasury.getAdjustedPrice(price,discount)-this.cut);
    }
    }
    public boolean read(String content)
    {
        return false;
    }
}

package com.MichaelHardityaJmartFA;

import com.MichaelHardityaJmartFA.dbjson.Serializable;
/**
 * Class for Coupon information data
 * @author Michael Harditya
 *
 */
public class Coupon extends Serializable
{
	/**
	 * Types of Coupon: DISCOUNT or REBATE
	 */
    public static enum Type{
        DISCOUNT,
        REBATE}
    public final int code;
    public final double cut;
    public final double minimum;
    public final String name;
    public final Type type;
    private boolean used;
    /**
     * Coupon data type
     * @param name Coupon name
     * @param code Coupon code (in numbers)
     * @param type Coupon Type {@link Type}
     * @param cut price substractor
     * @param minimum price minimal sum
     * @param used check if the Coupon is already used
     */
    public Coupon (String name, int code, Type type, double cut, double minimum)
    {
        this.name = name;
        this.code = code;
        this.type = type;
        this.cut = cut;
        this.minimum = minimum;
        this.used = false;
    }
    /**
     * Check if the Coupon has already used
     * @return
     */
    public boolean isUsed(){
        return used;
    }
    /**
     * Check if the Coupon can be applied or not
     * @param price product price to be applied after adjusted
     * @param discount to adjust the price
     * @return true if it can be applied
     */
    public boolean canApply(double price, double discount){
        if ((Treasury.getAdjustedPrice(price,discount)>=this.minimum)&&(this.used==false)){
            return true;
        }
        else{
            return false;
        }
    }
    /**
     * Applying coupon to the price, depends on the coupon types
     * @param price product price to be applied after adjusted
     * @param discount to adjust the price
     * @return Reduced and adjusted price
     */
    public double apply(double price, double discount) {
        if (canApply(price,discount)) {
        	this.used = true;
        	if (this.type == Coupon.Type.DISCOUNT){
                return (Treasury.getAdjustedPrice(price,discount)*(100-this.cut)/100);
            }
            else{
            return (Treasury.getAdjustedPrice(price,discount)-this.cut);
            }	
        }else {
        	return Treasury.getAdjustedPrice(price,discount);
        }
    }
    public boolean read(String content)
    {
        return false;
    }
}

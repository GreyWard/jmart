package com.MichaelHardityaJmartFA;
/**
 * Counting prices, fees, and discounts
 * @author Michael Harditya
 *
 */
public class Treasury
{

    public static final double COMMISSION_MULTIPLIER = 0.05;
    public static final double BOTTOM_PRICE = 20000.0;
    public static final double BOTTOM_FEE = 1000.0;

    public Treasury(double price){
    }
    public Treasury(double price, double discount){
    }
    /**
     * Counts the adjusted price
     * @param price the product price
     * @param discount the discount applied for the product
     * @return an adjusted price
     */
    public static double getAdjustedPrice(double price, double discount){
        return (getDiscountedPrice(price,discount)+getAdminFee(price,discount));
    }
    /**
     * Counts the Admin Fees for the price
     * @param price the product price
     * @param discount the discount applied for the product
     * @return admin fee for the product
     */
    public static double getAdminFee(double price, double discount){
        if (getDiscountedPrice(price,discount)<BOTTOM_PRICE){
            return BOTTOM_FEE;
        }
        else{
            return (getDiscountedPrice(price,discount)*COMMISSION_MULTIPLIER);
        }
    }
    /**
     * Counts the discounted price
     * @param price the product price
     * @param discount the discount applied for the product
     * @return discount applied price
     */
    public static double getDiscountedPrice(double price, double discount){
        if (discount > 100.0){
            return 0.0;
        }
        else if (discount == 100.0){
            return price;
        }
        else{
            return (price / 100 * (100 - discount));
        }
    }
}

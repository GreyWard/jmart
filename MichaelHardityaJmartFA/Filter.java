package MichaelHardityaJmartFA;

import java.util.ArrayList;
/**
 * Write a description of class Filter here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Filter
{
    /**
     * Constructor for objects of class Filter
     */
    public static void filterProductRating(ArrayList<ProductRating> list, double value, boolean less)
    {
        for (ProductRating iterate : list){
            if (less == true){
                if (iterate.getAverage() < value){
                    list.remove(iterate);
                }
            }   
            else if (less == false){
                if (iterate.getAverage() >= value){
                    list.remove(iterate);
                }
            } 
        }

    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public static ArrayList<PriceTag> filterPriceTag(PriceTag[] list, int value, boolean less)
    {
        ArrayList<PriceTag> dummy = new ArrayList<PriceTag>();
        for( PriceTag iterate : list){
            if (less == true){
                if (iterate.getAdjustedPrice() < value){
                  dummy.add(iterate);
                }
            }
            else if (less == false){
                if (iterate.getAdjustedPrice() >= value){
                    dummy.add(iterate);
                }
            }
        }
        return dummy;
    }
}

package com.MichaelHardityaJmartFA;
/**
 * Tracks the Product Rating
 * @author Michael Harditya
 *
 */
public class ProductRating
{
    private long total;
    private long count;
    /**
     * for all products, initiation starts from 0
     */
    public ProductRating(){
        this.total = 0;
        this.count = 0;
    }
    /**
     * to insert new Rating for the product
     * @param rating the given rates
     */
    public void insert(int rating){
        this.total += rating;
        this.count ++;
    }
    /**
     * to get average of the Rating
     * @return the average of the Rating
     */
    public double getAverage(){
        if (this.count != 0){
            return (this.total/this.count);
        }
        else{
            return 0;
        }
    }
    /**
     * get how many Rating has been done to the product
     * @return the counter
     */
    public long getCount(){
        return this.count;
    }
    /**
     * get total Rating of the product
     * @return total Rating
     */
    public long getTotal(){
        return this.total;
    }
}

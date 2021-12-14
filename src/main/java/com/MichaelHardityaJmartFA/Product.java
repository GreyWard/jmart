package com.MichaelHardityaJmartFA;

import com.MichaelHardityaJmartFA.dbjson.Serializable;

/**
 * Keeps Product information
 * @see Serializable
 * @see ProductCategory
 * @see Shipment
 * @author Michael Harditya
 */
public class Product extends Serializable
{   
    public int accountId;
    public ProductCategory category;
    public boolean conditionUsed;
    public double discount;
    public String name;
    public double price;
    public byte shipmentPlans;
    public int weight;
    /**
     * Keeps Product information
     * @param accountId the store ID that sells the product
     * @param name the name of the product
     * @param weight the weight of the product
     * @param conditionUsed the condition of the product
     * @param price the price of the product
     * @param discount the discount that applied for the product
     * @param category the category of the product
     * @param shipmentPlans the delivery plan for the product
     */
    public Product(int accountId, String name, int weight, boolean conditionUsed, double price, double discount, ProductCategory category, byte shipmentPlans){
        this.accountId = accountId;
        this.name = name;
        this.weight = weight;
        this.conditionUsed = conditionUsed;
        this.price = price;
        this.category = category;
        this.discount = discount;
        this.shipmentPlans = shipmentPlans;
    }
    public String toString(){
        return (name);
    }
}

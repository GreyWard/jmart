package MichaelHardityaJmartFA;


/**
 * Memuat data produk
 *
 * @author Michael Harditya
 */
public class Product extends Serializable
{   
    /**
     * Menyimpan data produk
     * @param storeId menyimpan id toko {@code Product}
     * @param name menyimpan nama {@code Product}
     * @param weight menyimpan berat {@code Product}
     * @param conditionUsed menyimpan kondisi {@code Product} apakah bekas atau baru
     * @param priceTag menyimpan harga {@code Product} pada class {@code priceTag}
     * @param category menyimpan kategori {@code Product} pada class {@code ProductCategory}
     * @param rating menyimpan rating {@code Product} pada class {@code ProductRating}
     */
    public int accountId;
    public ProductCategory category;
    public boolean conditionUsed;
    public double discount;
    public String name;
    public double price;
    public byte shipmentPlans;
    public int weight;
    /**
     * Menyimpan data produk
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
        return ("Name: "+name+"\nWeight: "+weight+"\nconditionUsed: "+conditionUsed+"\nprice: "+price+"\ndiscount: "+discount+"\ncategory: "+category+"\naccountId: "+accountId);
    }
}

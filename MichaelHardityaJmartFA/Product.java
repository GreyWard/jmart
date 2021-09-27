package MichaelHardityaJmartFA;


/**
 * Memuat data produk
 *
 * @author Michael Harditya
 */
public class Product extends Recognizable implements FileParser
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
    public int storeId;
    public String name;
    public int weight;
    public boolean conditionUsed;
    public PriceTag priceTag;
    public ProductCategory category;
    public ProductRating rating;
    /**
     * Menyimpan data produk
     */
    public Product(int id,int storeId, String name, int weight, boolean conditionUsed, PriceTag priceTag, ProductCategory category){
        super(id);
        this.storeId = storeId;
        this.name = name;
        this.weight = weight;
        this.conditionUsed = conditionUsed;
        this.priceTag = priceTag;
        this.category = category;
        this.rating = rating;
    }
    /**
     * Menyimpan data produk
     * @param storeId menyimpan id toko {@code Product} menggunakan class {@code Store}
     */
    public Product(int id, Store store, String name, int weight, boolean conditionUsed, PriceTag priceTag, ProductCategory category){
        super(id);
        this.storeId = store.id;
        this.name = name;
        this.weight = weight;
        this.conditionUsed = conditionUsed;
        this.priceTag = priceTag;
        this.category = category;
        this.rating = rating;
    }
    public boolean read(String content)
    {
        return false;
    }
}

package MichaelHardityaJmartFA;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
/**
 * a JMart Apps for managing a store
 * @author Michael Harditya
 * @version 0.0.1
 */
class Jmart
{

	public static List<Product> filterByCategory (List<Product> list, ProductCategory category){
		List<Product> filteredList = Algorithm.<Product>collect(list,prod -> prod.category == category);
		return filteredList;
	}
	public static List<Product> filterByPrice (List<Product> list, double minPrice, double maxPrice){
		List<Product> filteredList = new ArrayList<Product>();
		if (maxPrice == 0.0) {
			filteredList = Algorithm.<Product>collect(list,prod -> prod.price >= minPrice);
		}
		else if (minPrice == 0.0) {
			filteredList = Algorithm.<Product>collect(list,prod -> prod.price <= maxPrice);
		}
		else {
			filteredList = Algorithm.<Product>collect(list,prod -> prod.price <= maxPrice && prod.price >= minPrice);
		}
		return filteredList;
	}
	
    public static void main(String[] args){
    	try
    	{
    		List<Product> list = read("D:/JavaProject/jmart/lib/randomProductList.json");
    		List<Product> filtered = filterByPrice(list,0.0,20000.0);
    		filtered.forEach(product -> System.out.println(product.price));
    	}
    	catch (Throwable t)
    	{
    		t.printStackTrace();
    	}
    }
    private static List<Product> paginate (List<Product> list, int page, int pageSize, Predicate<Product> pred){
    	List<Product> paginatedList = new ArrayList<Product>();
    	int x = 0;
    	int start = page * pageSize;
    	int end = start + pageSize;
		for(Product check : list) {
			if (x>=start && x < end) {
			paginatedList.add(check);
			}
			x++;
		}
		return paginatedList;
    }
	public static List<Product> read(String string) throws FileNotFoundException {
		JsonReader readed = new JsonReader(new FileReader(string));
		Product[] result = new Gson().fromJson(readed, Product[].class);
		List<Product> list = Algorithm.<Product>collect(result,prod -> true);
		return list;
	}
}

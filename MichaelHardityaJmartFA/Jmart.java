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
	public static List<Product> filterByAccountId (List<Product> list, int accountId, int page, int pageSize){
		List<Product> filteredList = new ArrayList<Product>();
		for(Product check : list) {
			if (check.accountId == accountId) {
				filteredList.add(check);
			}
		}
		return Algorithm.<Product>paginate(filteredList,page,pageSize,pred -> true);
	}
	public static List<Product> filterByCategory (List<Product> list, ProductCategory category){
		List<Product> filteredList = Algorithm.<Product>collect(list,prod -> prod.category == category);
		return filteredList;
	}
	public static List<Product> filterByName (List<Product> list, String search, int page, int pageSize){
		List<Product> filteredList = new ArrayList<Product>();
		for (Product check : list) {
			if (check.name.toLowerCase().contains(search.toLowerCase())) {
				filteredList.add(check);
			}
		}
		return Algorithm.<Product>paginate(filteredList,page,pageSize,pred -> true);
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
    		String filepath = "a/b/account.json";
    		JsonTable<Account> tableAccount = new JsonTable<>(Account.class, filepath);
    		tableAccount.add(new Account("name","email","password",0));
    		tableAccount.writeJson();
    		tableAccount = new JsonTable<>(Account.class,filepath);
    		tableAccount.forEach(account -> System.out.println(account.toString()));
    		
    	}
    	catch (Throwable t)
    	{
    		t.printStackTrace();
    	}
    }
	public static List<Product> read(String string) throws FileNotFoundException {
		JsonReader readed = new JsonReader(new FileReader(string));
		Product[] result = new Gson().fromJson(readed, Product[].class);
		List<Product> list = Algorithm.<Product>collect(result,prod -> true);
		return list;
	}
}

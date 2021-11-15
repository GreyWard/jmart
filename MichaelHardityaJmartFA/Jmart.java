package MichaelHardityaJmartFA;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import MichaelHardityaJmartFA.Invoice.Status;
/**
 * a JMart Apps for managing a store
 * @author Michael Harditya
 * @version 0.0.1
 */
class Jmart
{
	protected static long DELIVERED_LIMIT_MS = 120000;
	protected static long ON_DELIVERY_LIMIT_MS = 100000;
	protected static long ON_PROGRESS_LIMIT_MS = 50000;
	protected static long WAITING_CONF_LIMIT_MS = 30000;
	/*public static List<Product> filterByAccountId (List<Product> list, int accountId, int page, int pageSize){
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
	}*/
	
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
    public static boolean paymentTimekeeper (Payment payment) {
    	long time = payment.history.get(payment.history.size()-1).date.getTime();
    	if (payment.history.get(payment.history.size()-1).status == Invoice.Status.WAITING_CONFIRMATION) {
    		if (time > WAITING_CONF_LIMIT_MS) {
    			Payment.Record newer = payment.new Record(Status.FAILED,"gagal dikonfirmasi");
    			payment.history.add(newer);
    		}
    	}
    	if (payment.history.get(payment.history.size()-1).status == Invoice.Status.ON_PROGRESS) {
    		if (time > ON_PROGRESS_LIMIT_MS) {
    			Payment.Record newer = payment.new Record(Status.FAILED,"gagal diproses");
    			payment.history.add(newer);
    		}
    	}
    	if (payment.history.get(payment.history.size()-1).status == Invoice.Status.ON_DELIVERY) {
    		if (time > ON_DELIVERY_LIMIT_MS) {
    			Payment.Record newer = payment.new Record(Status.DELIVERED,"sudah diantar");
    			payment.history.add(newer);
    		}
    	}
    	if (payment.history.get(payment.history.size()-1).status == Invoice.Status.DELIVERED) {
    		if (time > DELIVERED_LIMIT_MS) {
    			Payment.Record newer = payment.new Record(Status.FINISHED,"sudah selesai");
    			payment.history.add(newer);
    		}
    	}
    	return true;
    }
	/*public static List<Product> read(String string) throws FileNotFoundException {
		JsonReader readed = new JsonReader(new FileReader(string));
		Product[] result = new Gson().fromJson(readed, Product[].class);
		List<Product> list = Algorithm.<Product>collect(result,prod -> true);
		return list;
	}*/
}

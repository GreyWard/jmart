package com.MichaelHardityaJmartFA;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.util.ArrayList;
//import java.util.List;
//import com.google.gson.*;
//import com.google.gson.stream.JsonReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import com.MichaelHardityaJmartFA.Invoice.Status;
import com.MichaelHardityaJmartFA.dbjson.JsonDBEngine;
/**
 * This class runs the Spring Framework for backend purposes
 * @author Michael Harditya
 * @version 0.0.1
 */
@SpringBootApplication
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
	/**
	 * Runs the Jmart Backend on Spring Framework
	 * @param args
	 */
    public static void main(String[] args){
    	/*try
    	{
    		String filepath = "a/b/account.json";
    		JsonTable<Account> tableAccount = new JsonTable<>(Account.class, filepath);
    		tableAccount.add(new Account("name","email","password",0));
    		tableAccount.writeJson();
    		tableAccount = new JsonTable<>(Account.class,filepath);
    		tableAccount.forEach(account -> System.out.println(account.toString()));
    		JsonTable<Payment> table = new JsonTable<>(Payment.class, "lib/randomPaymentList.json");
    		ObjectPoolThread<Payment> paymentPool = new ObjectPoolThread<Payment>("Thread-PP",Jmart::paymentTimekeeper);
    		paymentPool.start();
    		table.forEach(payment -> paymentPool.add(payment));
    		while (paymentPool.size() != 0);
    		paymentPool.exit();
    		while(paymentPool.isAlive());
    		System.out.println("Thread exited successfully");
    		Gson gson = new Gson();
    		table.forEach(payment -> {
    			String history = gson.toJson(payment.history);
    			System.out.println(history);
    		});
    	}
    	catch (Throwable t)
    	{
    		t.printStackTrace();
    	}*/
    	JsonDBEngine.Run(Jmart.class);
    	SpringApplication.run(Jmart.class, args);
    	Runtime.getRuntime().addShutdownHook(new Thread(() -> JsonDBEngine.join()));
    }
	/*public static List<Product> read(String string) throws FileNotFoundException {
		JsonReader readed = new JsonReader(new FileReader(string));
		Product[] result = new Gson().fromJson(readed, Product[].class);
		List<Product> list = Algorithm.<Product>collect(result,prod -> true);
		return list;
	}*/
}

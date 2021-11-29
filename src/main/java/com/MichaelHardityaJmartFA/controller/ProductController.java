package com.MichaelHardityaJmartFA.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MichaelHardityaJmartFA.Account;
import com.MichaelHardityaJmartFA.Algorithm;
import com.MichaelHardityaJmartFA.Product;
import com.MichaelHardityaJmartFA.ProductCategory;
import com.MichaelHardityaJmartFA.dbjson.JsonAutowired;
import com.MichaelHardityaJmartFA.dbjson.JsonTable;

@RestController
@RequestMapping("/product")
public class ProductController implements BasicGetController<Product> {
	 @JsonAutowired(filepath = "a/b/product.json", value = Product.class)
	 public static JsonTable<Product> productTable;
	 @PostMapping("/create")
	 Product create
	 (
			 @RequestParam int accountId, 
			 @RequestParam String name, 
			 @RequestParam int weight, 
			 @RequestParam boolean conditionUsed, 
			 @RequestParam double price, 
			 @RequestParam double discount, 
			 @RequestParam ProductCategory category, 
			 @RequestParam byte shipmentPlans
	) 
	{
		 Account found = Algorithm.<Account>find(AccountController.accountTable,prod -> prod.id == accountId);
		 if (found != null) {
			 if (found.store != null) {
				productTable.add(new Product(accountId, name, weight, conditionUsed, price, discount, category, shipmentPlans));
				return new Product(accountId, name, weight, conditionUsed, price, discount, category, shipmentPlans);
			 } else {
				 return null;
			 }
		 }else {
			 return null;
		 }
	}
	 @GetMapping("/{id}/store")
	 List<Product> getProductByStore(@PathVariable int id, @RequestParam int page, @RequestParam int pageSize){
		try
		{
			List<Product> list = Algorithm.<Product>collect(productTable,prod -> prod.accountId == id);
			return Algorithm.<Product>paginate(list,page,pageSize,pred -> true);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	 }
	 @GetMapping("/getFiltered")
	 List<Product> getProductFiltered(@RequestParam int page, 
			 @RequestParam int pageSize, 
			 @RequestParam int accountId, 
			 @RequestParam String search, 
			 @RequestParam int minPrice, 
			 @RequestParam int maxPrice, 
			 @RequestParam ProductCategory category){
		 return null;
	 }

	public JsonTable<Product> getJsonTable() {
		return productTable;
	}

}

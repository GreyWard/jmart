package com.MichaelHardityaJmartFA.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MichaelHardityaJmartFA.Account;
import com.MichaelHardityaJmartFA.Algorithm;
import com.MichaelHardityaJmartFA.Product;
import com.MichaelHardityaJmartFA.ProductCategory;
import com.MichaelHardityaJmartFA.dbjson.JsonAutowired;
import com.MichaelHardityaJmartFA.dbjson.JsonTable;

@RestController
public class ProductController implements BasicGetController<Product> {
	 @JsonAutowired(filepath = "a/b/product.json", value = Product.class)
	 public static JsonTable<Product> productTable;
	 @PostMapping("/product/create")
	 Product create
	 (
			 int accountId, 
			 String name, 
			 int weight, 
			 boolean conditionUsed, 
			 double price, 
			 double discount, 
			 ProductCategory category, 
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
	 @GetMapping("/product/{id}/store")
	 List<Product> getProductByStore(@PathVariable int id, int page, int pageSize){
		try
		{
			List<Product> list = Algorithm.<Product>collect(productTable,prod -> prod.accountId == id);
			return Algorithm.<Product>paginate(list,page,pageSize,pred -> true);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	 }
	 @GetMapping("/product/getFiltered")
	 List<Product> getProductFiltered(int page, int pageSize, int accountId, String search, int minPrice, int maxPrice, ProductCategory category){
		 return null;
	 }
	@Override
	public JsonTable<Product> getJsonTable() {
		return productTable;
	}

}

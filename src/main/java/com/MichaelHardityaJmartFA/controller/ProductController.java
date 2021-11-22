package com.MichaelHardityaJmartFA.controller;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MichaelHardityaJmartFA.Account;
import com.MichaelHardityaJmartFA.Algorithm;
import com.MichaelHardityaJmartFA.Product;
import com.MichaelHardityaJmartFA.ProductCategory;
import com.MichaelHardityaJmartFA.dbjson.JsonAutowired;
import com.MichaelHardityaJmartFA.dbjson.JsonTable;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
@RestController
public class ProductController implements BasicGetController<Product> {
	 @JsonAutowired(filepath = "a/b/product.json", value = Product.class)
	 public static JsonTable<Product> productTable;
	 @PostMapping("/product/create")
	 Product create(int accountId, String name, int weight, boolean conditionUsed, double price, double discount, ProductCategory category, byte shipmentPlans) {
		 
		 return null;
	 }
	 @GetMapping("/product/{id}/store")
	 List<Product> getProductByStore(int id, int page, int pageSize){
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

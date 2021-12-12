package com.MichaelHardityaJmartFA.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MichaelHardityaJmartFA.TopUp;
import com.MichaelHardityaJmartFA.dbjson.JsonAutowired;
import com.MichaelHardityaJmartFA.dbjson.JsonTable;

@RestController
@RequestMapping("/topup")
public class TopUpController implements BasicGetController<TopUp>{
	 @JsonAutowired(filepath = "a/b/topup.json", value = TopUp.class) 
	    public static JsonTable<TopUp> topupTable;
	 @PostMapping("/create")
		TopUp topUp (@RequestParam String name, @RequestParam double balance) {
		 if (name.isBlank()||balance <=0) {
			 return null;
		 }else {
		 TopUp newCode = new TopUp(name,balance);
		 topupTable.add(newCode);
		 return newCode;
		 }
		}
	@Override
	public JsonTable<TopUp> getJsonTable() {
		return topupTable;
	}
}

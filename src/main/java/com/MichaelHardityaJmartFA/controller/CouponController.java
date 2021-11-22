package com.MichaelHardityaJmartFA.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MichaelHardityaJmartFA.Account;
import com.MichaelHardityaJmartFA.Algorithm;
import com.MichaelHardityaJmartFA.Coupon;
import com.MichaelHardityaJmartFA.Payment;
import com.MichaelHardityaJmartFA.Product;
import com.MichaelHardityaJmartFA.dbjson.JsonAutowired;
import com.MichaelHardityaJmartFA.dbjson.JsonTable;
@RestController
public class CouponController implements BasicGetController<Coupon>
{
	@JsonAutowired(filepath = "a/b/coupon.json", value = Coupon.class)
	 public static JsonTable<Coupon> couponTable;
	@GetMapping("/{id}/isUsed")
	boolean isUsed (int id) {
		Coupon found = Algorithm.<Coupon>find(couponTable,prod -> prod.id == id);
		if (found != null) {
			return found.isUsed();
		}else {
			return false;
		}
	}
	@GetMapping("/{id}/canApply")
	boolean canApply (int id, double price, double discount) {
		Coupon found = Algorithm.<Coupon>find(couponTable,prod -> prod.id == id);
		if (found != null) {
			return found.canApply(price,discount);
		}else {
			return false;
		}
	}
	@GetMapping("getAvailable")
	List<Coupon> getAvailable (int page, int pageSize){
		//List<Product> list = Algorithm.<Product>collect(couponTable,prod -> true);
		//return Algorithm.<Product>paginate(couponTable,page,pageSize,pred -> true);
	return null;
	}
	@Override
	public JsonTable<Coupon> getJsonTable() {
		return couponTable;
	}

}

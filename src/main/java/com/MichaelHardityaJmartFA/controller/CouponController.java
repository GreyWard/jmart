package com.MichaelHardityaJmartFA.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MichaelHardityaJmartFA.Algorithm;
import com.MichaelHardityaJmartFA.Coupon;
import com.MichaelHardityaJmartFA.dbjson.JsonAutowired;
import com.MichaelHardityaJmartFA.dbjson.JsonTable;
@RestController
@RequestMapping("/coupon")
public class CouponController implements BasicGetController<Coupon>
{
	@JsonAutowired(filepath = "a/b/coupon.json", value = Coupon.class)
	 public static JsonTable<Coupon> couponTable;
	@GetMapping("/{id}/isUsed")
	boolean isUsed (@PathVariable int id) {
		Coupon found = Algorithm.<Coupon>find(couponTable,prod -> prod.id == id);
		if (found != null) {
			return found.isUsed();
		}else {
			return false;
		}
	}
	@GetMapping("/{id}/canApply")
	boolean canApply (@PathVariable int id, @RequestParam("price") double price, @RequestParam("discount") double discount) {
		Coupon found = Algorithm.<Coupon>find(couponTable,prod -> prod.id == id);
		if (found != null) {
			return found.canApply(price,discount);
		}else {
			return false;
		}
	}
	@GetMapping("/getAvailable")
	List<Coupon> getAvailable (@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "0") int pageSize){
		List<Coupon> list = Algorithm.<Coupon>collect(couponTable,prod -> prod.isUsed() == false);
		return Algorithm.<Coupon>paginate(list,page,pageSize,pred -> true);
	}

	public JsonTable<Coupon> getJsonTable() {
		return couponTable;
	}

}

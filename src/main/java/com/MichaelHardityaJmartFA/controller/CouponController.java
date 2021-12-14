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
/**
 * Connect Coupon management between front end and back end
 * @see Coupon
 * @author Michael Harditya
 *
 */
@RestController
@RequestMapping("/coupon")
public class CouponController implements BasicGetController<Coupon>
{
	@JsonAutowired(filepath = "a/b/coupon.json", value = Coupon.class)
	 public static JsonTable<Coupon> couponTable;
	/**
	 * Check a coupon condition (already used or not)
	 * @param id coupon identifier
	 * @return boolean of the condition (true if used)
	 */
	@GetMapping("/{id}/isUsed")
	boolean isUsed (@PathVariable int id) {
		Coupon found = Algorithm.<Coupon>find(couponTable,prod -> prod.id == id);
		if (found != null) {
			return found.isUsed();
		}else {
			return false;
		}
	}
	/**
	 * Check if the Coupon can be applied or not
	 * @param id coupon identifier
     * @param price product price to be applied after adjusted
     * @param discount to adjust the price
     * @return true if it can be applied
	 */
	@GetMapping("/{id}/canApply")
	boolean canApply (@PathVariable int id, @RequestParam("price") double price, @RequestParam("discount") double discount) {
		Coupon found = Algorithm.<Coupon>find(couponTable,prod -> prod.id == id);
		if (found != null) {
			return found.canApply(price,discount);
		}else {
			return false;
		}
	}
	/**
	 * Get all available coupons (hasn't used yet) to front end
	 * @param page the page for {@code paginate} (default = 0)
	 * @param pageSize the page size for {@code paginate} (default = 0)
	 * @return coupon list
	 */
	@GetMapping("/getAvailable")
	List<Coupon> getAvailable (@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "0") int pageSize){
		List<Coupon> list = Algorithm.<Coupon>collect(couponTable,prod -> prod.isUsed() == false);
		return Algorithm.<Coupon>paginate(list,page,pageSize,pred -> true);
	}
	/**
	 * Get coupon Json Table (for back-end testing) 
	 */
	public JsonTable<Coupon> getJsonTable() {
		return couponTable;
	}

}

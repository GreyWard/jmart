package com.MichaelHardityaJmartFA;

import com.MichaelHardityaJmartFA.dbjson.Serializable;
/**
 * Keeps Top Up information "Product-like"
 * @author Michael Harditya
 *
 */
public class TopUp extends Serializable{
	public String couponCode;
	public double balance;
	/**
	 * Keeps Top Up coupons, every coupon have specific balance on it
	 * @param coupon the coupon code
	 * @param balance the balance on it
	 */
	public TopUp (String coupon, double balance) {
		this.couponCode = coupon;
		this.balance = balance;
	}
}

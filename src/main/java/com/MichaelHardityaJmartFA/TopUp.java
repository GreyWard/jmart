package com.MichaelHardityaJmartFA;

import com.MichaelHardityaJmartFA.dbjson.Serializable;

public class TopUp extends Serializable{
	public String couponCode;
	public double balance;
	public TopUp (String coupon, double balance) {
		this.couponCode = coupon;
		this.balance = balance;
	}
}

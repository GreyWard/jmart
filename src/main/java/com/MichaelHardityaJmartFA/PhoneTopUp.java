package com.MichaelHardityaJmartFA;


public class PhoneTopUp extends Invoice {
	String phoneNumber;
	Status status;
	public PhoneTopUp (int buyerId, int productId, String phoneNumber) {
		super(buyerId,productId);
		this.phoneNumber = phoneNumber;
		this.status = null; //will be edited later
	}
	public double getTotalPay (Product product) {
		return Treasury.getAdjustedPrice(product.price, product.discount);
	}
}

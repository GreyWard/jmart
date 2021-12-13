package com.MichaelHardityaJmartFA;

/**
 * Class for Phone Top Ups using Invoice
 * @author Michael Harditya
 *
 */
public class PhoneTopUp extends Invoice {
	String phoneNumber;
	Status status;
	/**
	 * Creating new phone top ups using specified Status, Ids, and phoneNumber
	 * @param buyerId the ID of the purchaser
	 * @param productId the ID of the product (refers to a TopUp Products)
	 * @param phoneNumber the phone number
	 */
	public PhoneTopUp (int buyerId, int productId, String phoneNumber) {
		super(buyerId,productId);
		this.phoneNumber = phoneNumber;
		this.status = null; //will be edited later
	}
	/**
	 * Count the amount needs to be paid
	 * @return adjusted price in double
	 */
	public double getTotalPay (Product product) {
		return Treasury.getAdjustedPrice(product.price, product.discount);
	}
}

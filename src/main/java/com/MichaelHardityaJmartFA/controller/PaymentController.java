package com.MichaelHardityaJmartFA.controller;

import com.MichaelHardityaJmartFA.Account;
import com.MichaelHardityaJmartFA.Payment;
import com.MichaelHardityaJmartFA.dbjson.JsonAutowired;
import com.MichaelHardityaJmartFA.dbjson.JsonTable;

public class PaymentController implements BasicGetController<Payment>
{
	@JsonAutowired(filepath = "a/b/payment.json", value = Payment.class) 
    public static JsonTable<Payment> paymentTable;
	public JsonTable getJsonTable() {
		return paymentTable;
	}

}

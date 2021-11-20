package com.MichaelHardityaJmartFA.controller;

import com.MichaelHardityaJmartFA.Account;
import com.MichaelHardityaJmartFA.Payment;
import com.MichaelHardityaJmartFA.dbjson.JsonAutowired;
import com.MichaelHardityaJmartFA.dbjson.JsonTable;

public class PaymentController implements BasicGetController<Account>
{
	@JsonAutowired(filepath = "a/b/payment.json", value = Account.class) 
    public static JsonTable<Payment> paymentTable;
	public JsonTable getJsonTable() {
		return paymentTable;
	}

}

package com.MichaelHardityaJmartFA.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.MichaelHardityaJmartFA.Account;
import com.MichaelHardityaJmartFA.Invoice;
import com.MichaelHardityaJmartFA.Payment;
import com.MichaelHardityaJmartFA.Invoice.Status;
import com.MichaelHardityaJmartFA.ObjectPoolThread;
import com.MichaelHardityaJmartFA.Payment.Record;
import com.MichaelHardityaJmartFA.dbjson.JsonAutowired;
import com.MichaelHardityaJmartFA.dbjson.JsonTable;

public class PaymentController implements BasicGetController<Payment>
{
	protected static long DELIVERED_LIMIT_MS = 120000;
	protected static long ON_DELIVERY_LIMIT_MS = 100000;
	protected static long ON_PROGRESS_LIMIT_MS = 50000;
	protected static long WAITING_CONF_LIMIT_MS = 30000;
	@JsonAutowired(filepath = "a/b/payment.json", value = Payment.class) 
    public static JsonTable<Payment> paymentTable;
	ObjectPoolThread<Payment> poolThread = new ObjectPoolThread<Payment>("Thread-PP",PaymentController::timekeeper);
	//notyet done
	@PostMapping("/payment/{id}/accept")
	boolean accept(@RequestParam int id)
	{
		return false;
	}
	@PostMapping("/payment/{id}/cancel")
	boolean cancel(@RequestParam int id) {
		return false;
	}
	@PostMapping("/payment/{id}/submit")
	boolean submit(@RequestParam int id) {
		return false;
	}
	public JsonTable getJsonTable() {
		return paymentTable;
	}
	//not yet done
	public static boolean timekeeper (Payment payment) {
    	long time = payment.history.get(payment.history.size()-1).date.getTime();
    	if (payment.history.get(payment.history.size()-1).status == Invoice.Status.WAITING_CONFIRMATION) {
    		if (time > WAITING_CONF_LIMIT_MS) {
    			Payment.Record newer = payment.new Record(Status.FAILED,"gagal dikonfirmasi");
    			payment.history.add(newer);
    		}
    	}
    	if (payment.history.get(payment.history.size()-1).status == Invoice.Status.ON_PROGRESS) {
    		if (time > ON_PROGRESS_LIMIT_MS) {
    			Payment.Record newer = payment.new Record(Status.FAILED,"gagal diproses");
    			payment.history.add(newer);
    		}
    	}
    	if (payment.history.get(payment.history.size()-1).status == Invoice.Status.ON_DELIVERY) {
    		if (time > ON_DELIVERY_LIMIT_MS) {
    			Payment.Record newer = payment.new Record(Status.DELIVERED,"sudah diantar");
    			payment.history.add(newer);
    		}
    	}
    	if (payment.history.get(payment.history.size()-1).status == Invoice.Status.DELIVERED) {
    		if (time > DELIVERED_LIMIT_MS) {
    			Payment.Record newer = payment.new Record(Status.FINISHED,"sudah selesai");
    			payment.history.add(newer);
    		}
    	}
    	return true;
    }
}

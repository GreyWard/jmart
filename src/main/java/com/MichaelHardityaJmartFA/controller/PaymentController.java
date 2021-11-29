package com.MichaelHardityaJmartFA.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MichaelHardityaJmartFA.Account;
import com.MichaelHardityaJmartFA.Algorithm;
import com.MichaelHardityaJmartFA.Invoice;
import com.MichaelHardityaJmartFA.Payment;
import com.MichaelHardityaJmartFA.Product;
import com.MichaelHardityaJmartFA.Shipment;
import com.MichaelHardityaJmartFA.Invoice.Status;
import com.MichaelHardityaJmartFA.ObjectPoolThread;
import com.MichaelHardityaJmartFA.dbjson.JsonAutowired;
import com.MichaelHardityaJmartFA.dbjson.JsonTable;
@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment>
{
	protected static long DELIVERED_LIMIT_MS = 120000;
	protected static long ON_DELIVERY_LIMIT_MS = 100000;
	protected static long ON_PROGRESS_LIMIT_MS = 50000;
	protected static long WAITING_CONF_LIMIT_MS = 30000;
	@JsonAutowired(filepath = "a/b/payment.json", value = Payment.class) 
    public static JsonTable<Payment> paymentTable;
	ObjectPoolThread<Payment> poolThread = new ObjectPoolThread<Payment>("Thread-PP",PaymentController::timekeeper);
	@PostMapping("/create")
	Payment create (@RequestParam int buyerId,
			@RequestParam int productId, 
			@RequestParam int productCount, 
			@RequestParam String shipmentAddress, 
			@RequestParam byte shipmentPlan) {
		Account foundAcc = Algorithm.<Account>find(AccountController.accountTable,prod -> prod.id == buyerId);
		Product foundProd = Algorithm.<Product>find(ProductController.productTable,pred -> pred.id == productId);
		if (foundAcc != null && foundProd != null) {
			Shipment createShip = new Shipment(shipmentAddress, 0, shipmentPlan, null);
			Payment createPay = new Payment(buyerId, productId, productCount, createShip);
			double price = createPay.getTotalPay(foundProd);
			double balance = foundAcc.balance;
			if ((balance-price)>0) {
				foundAcc.balance -= price;
				Payment.Record newer = createPay.new Record(Status.WAITING_CONFIRMATION,"Pesanan menunggu konfirmasi");
				createPay.history.add(newer);
				paymentTable.add(createPay);
				poolThread.add(createPay);
				return createPay;
			} else {
				return null;
			}
		}else {
			return null;
		}
	}
	@PostMapping("/{id}/accept")
	boolean accept(@PathVariable int id)
	{
		Payment found = Algorithm.<Payment>find(paymentTable,prod -> prod.id == id);
		if (found != null && found.history.get(found.history.size()-1).status == Invoice.Status.WAITING_CONFIRMATION) {
			Payment.Record newer = found.new Record(Status.ON_PROGRESS,"Pesanan diterima");
			found.history.add(newer);
			return true;
		}else {
			return false;
		}
	}
	@PostMapping("/{id}/cancel")
	boolean cancel(@PathVariable int id) {
		Payment found = Algorithm.<Payment>find(paymentTable,prod -> prod.id == id);
		if (found != null && found.history.get(found.history.size()-1).status == Invoice.Status.WAITING_CONFIRMATION) {
			Payment.Record newer = found.new Record(Status.CANCELLED,"Pesanan diterima");
			found.history.add(newer);
			return true;
		}else {
			return false;
		}
	}
	@PostMapping("/{id}/submit")
	boolean submit(@PathVariable int id, 
				   @RequestParam String receipt) {
		Payment found = Algorithm.<Payment>find(paymentTable,prod -> prod.id == id);
		if (found != null && found.history.get(found.history.size()-1).status == Invoice.Status.ON_PROGRESS) {
			if (receipt.isBlank()) {
				return false;
			}else {
				found.shipment.receipt = receipt;
				Payment.Record newer = found.new Record(Status.ON_PROGRESS,"Pesanan diterima");
				found.history.add(newer);
				return true;
			}
		}else {
			return false;
		}
	}
	public JsonTable<Payment> getJsonTable() {
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

package com.MichaelHardityaJmartFA.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
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
/**
 * Connect Payment management between front end and back end
 * @see Payment
 * @author Michael Harditya
 *
 */
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
	static ObjectPoolThread<Payment> poolThread;
	static {
		poolThread = new ObjectPoolThread<Payment>("Thread-PP",PaymentController::timekeeper);
		poolThread.start();
	}
	/**
	 * Create new Payment from front-end, also decrease the buyer account balance based on the product price
	 * @param buyerId Buyer identifier
	 * @param productId Product identifier
	 * @param productCount quantity of the product
	 * @param shipmentAddress address for delivery
	 * @param shipmentPlan plan of the delivery
	 * @return Payment object if succeed, null if not
	 */
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
			Payment createPay = new Payment(buyerId, productId, productCount, createShip,foundProd.name);
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
	/**
	 * Accept method for a Payment object, changes it status to ON_PROGRESS only if the present status is WAITING_CONFIRMATION.
	 * Only store managers can call this method.
	 * @param id identifier of the payment object
	 * @return true if it succeed, false if not
	 */
	@PostMapping("/{id}/accept")
	boolean accept(@PathVariable int id)
	{
		Payment found = Algorithm.<Payment>find(paymentTable,prod -> prod.id == id);
		poolThread.add(found);
		if (found != null && found.history.get(found.history.size()-1).status == Invoice.Status.WAITING_CONFIRMATION) {
			Payment.Record newer = found.new Record(Status.ON_PROGRESS,"Pesanan sedang dikerjakan");
			found.status = Status.ON_PROGRESS;
			found.history.add(newer);
			return true;
		}else {
			return false;
		}
	}
	/**
	 * Cancel method for Payment object, changes it status to CANCELLED only if the present status is WAITING_CONFIRMATION.
	 * Both buyer and store manager can call this method.
	 * @param id identifier for the payment object
	 * @return true if succeed, false if not
	 */
	@PostMapping("/{id}/cancel")
	boolean cancel(@PathVariable int id) {
		Payment found = Algorithm.<Payment>find(paymentTable,prod -> prod.id == id);
		Account foundAcc = Algorithm.<Account>find(AccountController.accountTable,prod -> prod.id == found.buyerId);
		Product foundProd = Algorithm.<Product>find(ProductController.productTable,pred -> pred.id == found.productId);
		if (found != null && found.history.get(found.history.size()-1).status == Invoice.Status.WAITING_CONFIRMATION) {
			Payment.Record newer = found.new Record(Status.CANCELLED,"Pesanan dibatalkan");
			found.status = Status.CANCELLED;
			found.history.add(newer);
			foundAcc.balance += found.getTotalPay(foundProd);
			return true;
		}else {
			return false;
		}
	}
	/**
	 * Submit method for a Payment object, changes it status to ON_DELIVERY only if the present status is ON_PROGRESS.
	 * Only store managers can call this method.
	 * @param id identifier for the Payment object
	 * @param receipt receipt for buyer and courier, configured in the front-end and added the total payment of the product in here.
	 * @return true if succeed, false if not
	 */
	@PostMapping("/{id}/submit")
	boolean submit(@PathVariable int id, 
				   @RequestParam String receipt) {
		Payment found = Algorithm.<Payment>find(paymentTable,prod -> prod.id == id);
		Product checkProd = Algorithm.<Product>find(ProductController.productTable,pred -> pred.id == found.productId);
		if (checkProd != null && found != null && found.history.get(found.history.size()-1).status == Invoice.Status.ON_PROGRESS) {
			if (receipt.isBlank()) {
				return false;
			}else {
				found.shipment.receipt = receipt + "price=" + found.getTotalPay(checkProd) +"}";
				Payment.Record newer = found.new Record(Status.ON_DELIVERY,"Pesanan dikirim");
				found.history.add(newer);
				found.status = Status.ON_DELIVERY;
				return true;
			}
		}else {
			return false;
		}
	}
	/**
	 * 	/**
	 * Get account invoices (a list of Payment object), used to populate Payment list for account in front-end
	 * @param buyerId buyer account identifier
	 * @param page page for {@code paginate}
	 * @param pageSize page size for {@code paginate}
	 * @return paginated list of Payment
	 */
	@GetMapping("/getPayment")
	 List<Payment> getPayment(@RequestParam int buyerId,
			 @RequestParam int page,
			 @RequestParam int pageSize){
		 try
		 {
			 List<Payment> filterId = null;
			 filterId = Algorithm.<Payment>collect(paymentTable,prod -> prod.buyerId == buyerId);
			 List<Payment> paginated = Algorithm.<Payment>paginate(filterId, page, pageSize, prod -> true);
			 return paginated;
		 }catch(Exception e){
			 e.printStackTrace();
			 return null;
		 }
	 }
	/**
	 * Get store orders (a list of Payment object), used to populate and manage the Payment for the store in front-end
	 * @param accountId identifier for the store owner
	 * @param page page for {@code paginate}
	 * @param pageSize page size for {@code paginate}
	 * @return paginated list of Payment
	 */
	@GetMapping("/getOrder")
	 List<Payment> getOrder(@RequestParam int accountId,
			 @RequestParam int page,
			 @RequestParam int pageSize){
		 try
		 {
			 List<Payment> filterId = new ArrayList<>();
			 List<Product> soldItems = Algorithm.<Product>collect(ProductController.productTable,prod -> prod.accountId == accountId);
			 for (Product iter : soldItems) {
				 List<Payment> filterItem = Algorithm.<Payment>collect(paymentTable,prod -> prod.productId == iter.id);
				 filterId.addAll(filterItem);
			 }
			 List<Payment> paginated = Algorithm.<Payment>paginate(filterId, page, pageSize, prod -> true);
			 return paginated;
		 }catch(Exception e){
			 e.printStackTrace();
			 return null;
		 }
	 }
	/**
	 * For back-end purpose, get payment Json Table
	 */
	public JsonTable<Payment> getJsonTable() {
		return paymentTable;
	}
/**
 * Updates the status overtime, if the maximum time has reached, the status will changed to Failed
 * @param payment payment object that needs to be updated
 */
	public static boolean timekeeper (Payment payment) {
		long now = new Date().getTime();
    	long time = now - payment.history.get(payment.history.size()-1).date.getTime();
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

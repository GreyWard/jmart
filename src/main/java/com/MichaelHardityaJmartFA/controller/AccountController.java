package com.MichaelHardityaJmartFA.controller;


import com.MichaelHardityaJmartFA.Account;
import com.MichaelHardityaJmartFA.Algorithm;
import com.MichaelHardityaJmartFA.Store;
import com.MichaelHardityaJmartFA.TopUp;
import com.MichaelHardityaJmartFA.dbjson.JsonAutowired;
import com.MichaelHardityaJmartFA.dbjson.JsonTable;
//import com.MichaelHardityaJmartFA.dbjson.Serializable;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
//import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.web.bind.annotation.*;
/**
 * Controls Account data types
 * @author Michael Harditya
 *
 */
@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account>
{
	public static final String REGEX_EMAIL = "^[A-Za-z0-9&_*~]+(?:\\.[A-Za-z0-9&_*~]+)*@(?:[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?\\.)+[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?$";
    public static final String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
	public static final Pattern REGEX_PATTERN_EMAIL = Pattern.compile(REGEX_EMAIL);
	public static final Pattern REGEX_PATTERN_PASSWORD = Pattern.compile(REGEX_PASSWORD);
    @JsonAutowired(filepath = "a/b/account.json", value = Account.class) 
    public static JsonTable<Account> accountTable;
	@GetMapping
	String index() { return "account page"; }
	/**
	 * used to login from frontend
	 * @param email user input email
	 * @param password user input password
	 * @return the account itself if succeed, null if failed
	 */
	@PostMapping("/login")
	Account login (@RequestParam String email, @RequestParam String password) {
		Account found = Algorithm.<Account>find(accountTable,prod -> prod.email.equals(email));
		String generatedPass = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] bytes = md.digest();
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPass = sb.toString();
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		if (found != null && found.password.equals(generatedPass)) {
			return found;
		}else {
        return null;
		}
	}
	/**
	 * used to register new Account (next update, reject if the email is already exists)
	 * @param name user input name
	 * @param email user input email, succeed when the email is in correct regex
	 * @param password user input password, succeed when the password is in correct regex
	 * @return the account itself if succeed, null if failed
	 */
	@PostMapping("/register")
	Account register
	(
			@RequestParam String name,
			@RequestParam String email,
			@RequestParam String password
	)
	{
		Matcher mail = REGEX_PATTERN_EMAIL.matcher(email);
        Matcher pass = REGEX_PATTERN_PASSWORD.matcher(password);
		if (name.isBlank()) {
			return null;
		}
		else if(mail.find() && pass.find()) {
			String generatedPass = null;
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(password.getBytes());
				byte[] bytes = md.digest();
				StringBuilder sb = new StringBuilder();
				for(int i = 0; i < bytes.length; i++) {
					sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
				}
				generatedPass = sb.toString();
			}catch(NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			Account made = new Account(name, email, generatedPass, 0);
			accountTable.add(made);
			return made;
		}
		else {
			return null;
		}
	}
	/**
	 * used to register a Store in the Account, only if it doesn't have a Store yet
	 * @param id user ID, generated automatically for every account
	 * @param name username, taken from the user Account
	 * @param address the address details from the user Account
	 * @param phoneNumber the phone number of the Store
	 * @return the Account Store data
	 */
	@PostMapping("/{id}/registerStore")
	Store registerStore
	(
		@PathVariable int id,
		@RequestParam String name,
		@RequestParam String address,
		@RequestParam String phoneNumber
	)
	{
		Account found = Algorithm.<Account>find(accountTable,prod -> prod.id == id);
		if (found.store == null) {
			found.store = new Store(name,address,phoneNumber,0);
			return found.store;
		}else {
			return null;
		}
	}
	/**
	 * used to add balance into the Account
	 * @param id user ID, generated automatically for every account
	 * @param couponCode a string of coupon code
	 * @return true if succeed, false if failed
	 */
	@PostMapping("/{id}/topUp")
	TopUp topUp (@PathVariable int id, @RequestParam String couponCode) {
		Account found = Algorithm.<Account>find(accountTable,prod -> prod.id == id);
		TopUp topUps = Algorithm.<TopUp>find(TopUpController.topupTable,pred -> pred.couponCode.contentEquals(couponCode));
		if (found != null && topUps != null) {
			found.balance += topUps.balance;
			return topUps;
		}else {
			return null;
		}
	}
	public JsonTable<Account> getJsonTable() {
		return accountTable;
	}

}
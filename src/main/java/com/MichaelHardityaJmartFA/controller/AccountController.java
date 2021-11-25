package com.MichaelHardityaJmartFA.controller;


import com.MichaelHardityaJmartFA.Account;
import com.MichaelHardityaJmartFA.Algorithm;
import com.MichaelHardityaJmartFA.Store;
import com.MichaelHardityaJmartFA.dbjson.JsonAutowired;
import com.MichaelHardityaJmartFA.dbjson.JsonTable;
//import com.MichaelHardityaJmartFA.dbjson.Serializable;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
//import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController implements BasicGetController<Account>
{
	public static final String REGEX_EMAIL = "^(?!.)[A-Za-z0-9&_*~][A-Za-z0-9&_*~.]+@[^. -][-.A-Za-z0-9]+$";
    public static final String REGEX_PASSWORD = "^(?! )(?=[A-Za-z0-9])[A-Za-z0-9]{8}$";
	public static final Pattern REGEX_PATTERN_EMAIL = Pattern.compile(REGEX_EMAIL);
	public static final Pattern REGEX_PATTERN_PASSWORD = Pattern.compile(REGEX_PASSWORD);
    @JsonAutowired(filepath = "a/b/account.json", value = Account.class) 
    public static JsonTable<Account> accountTable;
	@GetMapping
	String index() { return "account page"; }
	@PostMapping("/account/login")
	Account login (String email, String password) {
		Account found = Algorithm.<Account>find(accountTable,prod -> prod.email == email);
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
		if (found.password == generatedPass) {
			return found;
		}else {
			return null;
		}
	}
	@PostMapping("/account/register")
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
			return new Account(name, email, generatedPass, 0);
		}
		else {
			return null;
		}
	}
	@PostMapping("/account/{id}/registerStore")
	Store registerStore
	(
		@RequestParam int id,
		@RequestParam String name,
		@RequestParam String address,
		@RequestParam String phoneNumber
	)
	{
		Account found = Algorithm.<Account>find(accountTable,prod -> prod.id == id);
		if (found.store == null) {
			found.store.address = address;
			found.store.phoneNumber = phoneNumber;
			found.store.name = name;
		}
		return found.store;
	}
	@PostMapping("/account/{id}/topUp")
	boolean topUp (int id, double balance) {
		Account found = Algorithm.<Account>find(accountTable,prod -> prod.id == id);
		if (found != null) {
			found.balance += balance;
			return true;
		}else {
			return false;
		}
	}

	public JsonTable<Account> getJsonTable() {
		return accountTable;
	}

}
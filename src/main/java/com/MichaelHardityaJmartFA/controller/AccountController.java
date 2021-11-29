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
//	@RequestMapping(value="/login", method=RequestMethod.POST)
//    Account login
//    (
//        @RequestParam String email,
//        @RequestParam String password
//    )
//    {
//        Account acc = Algorithm.<Account>find(accountTable, obj -> obj.email.equals(email));
//        return acc != null && acc.password.equals(password) ? acc : null;
//    }
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
			found.store.address = address;
			found.store.phoneNumber = phoneNumber;
			found.store.name = name;
		}
		return found.store;
	}
	@PostMapping("/{id}/topUp")
	boolean topUp (@PathVariable int id, @RequestParam double balance) {
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
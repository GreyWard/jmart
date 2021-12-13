package com.MichaelHardityaJmartFA;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Keeps Store information for the Account
 * @author Michael Harditya
 *
 */
public class Store
{
    public static final String REGEX_NAME = "[A-Z][a-zA-Z0-9]{3,19}[ ]{1}?";
    public static final String REGEX_PHONE = "[0-9]{9,12}";
    public String name;
    public String address;
    public String phoneNumber;
    public double balance;
    /**
     * Keeps Store information such as store name, address, phone number, and balance
     * @param name the store name
     * @param address the store address
     * @param phoneNumber the store phone number
     * @param balance the store balance
     */
    public Store(String name,String address,String phoneNumber,double balance)
    {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }
    /**
     * return the name, address, and phone number of the store
     */
    public String toString()
    {
        return ("name: "+name+"\naddress: "+address+"\nphoneNumber: "+phoneNumber);
    }
    /**
     * @deprecated validates the name and phone to a specific pattern, already implemented in the constructor
     * @return true if it is correct
     */
    public boolean validate(){
        Pattern patName = Pattern.compile(REGEX_NAME);
        Pattern patPhone = Pattern.compile(REGEX_PHONE);
        Matcher name = patName.matcher(this.name);
        Matcher phone = patPhone.matcher(this.phoneNumber);
        return (name.find() && phone.find());
    }
}

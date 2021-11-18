package com.MichaelHardityaJmartFA;
/**menyimpan data complain
   *@author Michael Harditya*/
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Complaint extends Serializable
{
    public Date date;
    public String desc;
   /**@param id berisikan id transaksi {@code Complaint}
   *@param payment berisikan id pembeli ({@code Payment.buyerId}), id toko ({@code Payment.storeId}) dan id pembayaran ({@code Payment.id})
   *@param desc berisikan deskripsi komplain {@code Complaint}*/
    public Complaint(String desc)
    {
        this.date = new Date();
        this.desc = desc;
    }
    public boolean read(String content)
    {
        return false;
    }
    public String toString(){
        Calendar date = Calendar.getInstance();
        date.setTime(this.date);
        SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");
        return ("Complaint{date="+form.format(date.getTime())+",desc='"+desc+"'}");
    }
}

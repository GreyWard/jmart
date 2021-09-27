package MichaelHardityaJmartFA;
/**menyimpan data complain
   *@author Michael Harditya*/
public class Complaint extends Transaction implements FileParser
{
    public int paymentId;
    public String desc;
   /**@param id berisikan id transaksi {@code Complaint}
   *@param payment berisikan id pembeli ({@code Payment.buyerId}), id toko ({@code Payment.storeId}) dan id pembayaran ({@code Payment.id})
   *@param desc berisikan deskripsi komplain {@code Complaint}*/
    public Complaint(int id, Payment payment, String desc)
    {
        super(id,payment.buyerId,payment.storeId);
        this.paymentId = payment.id;
        this.desc = desc;
    }
   /**@param id berisikan id transaksi {@code Complaint}
   *@param buyerId berisikan id pembeli {@code Complaint}
   *@param storeId berisikan id toko {@code Complaint}
   *@param paymentId berisikan id pembayaran {@code Complaint}
   *@param desc berisikan deskripsi komplain {@code Complaint}*/
    public Complaint(int id, int buyerId, int storeId, int paymentId, String desc)
    {
        super(id,buyerId,storeId);
        this.paymentId = paymentId;
        this.desc = desc;
    }
    public boolean validate()
    {
        return false;
    }
    public Transaction perform()
    {
        return null;
    }
    public boolean read(String content)
    {
        return false;
    }
}

package MichaelHardityaJmartFA;
/**menyimpan data complain
   *@author Michael Harditya*/
public class Complaint extends Recognizable implements FileParser
{
    public String date;
    public String desc;
   /**@param id berisikan id transaksi {@code Complaint}
   *@param payment berisikan id pembeli ({@code Payment.buyerId}), id toko ({@code Payment.storeId}) dan id pembayaran ({@code Payment.id})
   *@param desc berisikan deskripsi komplain {@code Complaint}*/
    public Complaint(int id, String desc)
    {
        super(id);
        this.date = "Melintasi waktu dan tempat";
        this.desc = desc;
    }
    public boolean read(String content)
    {
        return false;
    }
}

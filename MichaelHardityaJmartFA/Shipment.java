package MichaelHardityaJmartFA;


/**
 * Write a description of class Shipment here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
public class Shipment
{
    public String address;
    public int shipmentCost;
    public Duration duration;
    public String receipt;
    /**
     * Constructor for objects of class Shipment
     */
    public Shipment(String address, int shipmentCost, Duration duration, String receipt)
    {
        this.address = address;
        this.shipmentCost = shipmentCost;
        this.duration = duration;
        this.receipt = receipt;
    }
    public boolean read(String content)
    {
        return false;
    }
    public static class Duration
    {
    public static Duration INSTANT = new Duration((byte)(1 << 0));
    public static Duration SAME_DAY = new Duration((byte)(1 << 1));
    public static Duration NEXT_DAY = new Duration((byte)(1 << 2));
    public static Duration REGULER = new Duration((byte)(1 << 3));
    public static Duration KARGO = new Duration((byte)(1 << 4));
    public static final SimpleDateFormat ESTIMATION_FORMAT = new SimpleDateFormat("dd/MM/DD/yyyy");
    public byte bit;
    private Duration(byte bit){
        this.bit = bit;
    }
    public String getEstimatedArrival(Date reference){
        Calendar now = Calendar.getInstance();
        now.setTime(reference);
        if (bit == 2){
            now.add(Calendar.DATE,1);
        }
        else if(bit == 3){
            now.add(Calendar.DATE,2);
        }
        else if(bit == 4){
            now.add(Calendar.DATE,5);            
        }
        return Shipment.Duration.ESTIMATION_FORMAT.format(now.getTime());
    }
}
    public class MultiDuration
    {
        public byte bit;
        public MultiDuration(Duration... args){
        for (Duration i:args){
            this.bit |= i.bit;
        }
        }
    public boolean isDuration(Duration reference){
        if ((this.bit & reference.bit) == reference.bit){
            return true;
        }
        else{
            return false;
        }
    }
    }
}

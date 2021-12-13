package com.MichaelHardityaJmartFA;


/**
 * To process Shipment information, from plan to date
 *
 * @author Michael Harditya
 */
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
public class Shipment
{
	public static Plan INSTANT = new Plan((byte)(1 << 0));
    public static Plan SAME_DAY = new Plan((byte)(1 << 1));
    public static Plan NEXT_DAY = new Plan((byte)(1 << 2));
    public static Plan REGULER = new Plan((byte)(1 << 3));
    public static Plan KARGO = new Plan((byte)(1 << 4));
    public static final SimpleDateFormat ESTIMATION_FORMAT = new SimpleDateFormat("dd/MM/DD/yyyy");
    public String address;
    public int cost;
    public String receipt;
    public byte plan;
    /**
     * Initiate a new Shipment
     * @param address destination address for the shipment
     * @param cost cost needed for the shipment
     * @param plan shipment plan
     * @param receipt a receipt notes for the shipment delivery
     */
    public Shipment(String address, int cost, byte plan, String receipt)
    {
        this.address = address;
        this.cost = cost;
        this.plan = plan;
        this.receipt = receipt;
    }
    /**
     * Checking the estimation arrival for the referenced date, based on the plan
     * @param reference the referenced date
     * @return Shipment formatted date
     */
    public String getEstimatedArrival(Date reference){
        Calendar now = Calendar.getInstance();
        now.setTime(reference);
        if (plan == 2){
            now.add(Calendar.DATE,1);
        }
        else if(plan == 3){
            now.add(Calendar.DATE,2);
        }
        else if(plan == 4){
            now.add(Calendar.DATE,5);            
        }
        return Shipment.ESTIMATION_FORMAT.format(now.getTime());
    }
    /**
     * Check if the referenced plan is the Shipment plan
     * @param reference the referenced plan
     * @return true if it is the same plan
     */
    public boolean isDuration(Plan reference){
        if ((this.plan & reference.bit) == reference.bit){
            return true;
        }
        else{
            return false;
        }
    }
    /**
     * Check if the referenced plan is the Object plan
     * @param object the Object plan in byte
     * @param reference the referenced plan
     * @return true if it is the same plan
     */
    public static boolean isDuration(byte object, Plan reference){
        if ((object & reference.bit) == reference.bit){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean read(String content)
    {
        return false;
    }
    /**
     * The Plan class, have a byte type data
     * @author Michael Harditya
     *
     */
    public static class Plan
    {
    public final byte bit;
    private Plan (byte bit){
        this.bit = bit;
    }
}
}
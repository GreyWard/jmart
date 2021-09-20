package MichaelHardityaJmartFA;


public class ShipmentDuration
{
    public static final int INSTANT = 0 << 1;
    public static final int SAME_DAY = 1 << 1;
    public static final int NEXT_DAY = 2 << 1;
    public static final int REGULER = 3 << 1;
    public static final int KARGO = 4 << 1;
    private int bit;
    private ShipmentDuration(int bit){
        this.bit = bit;
    }
    public ShipmentDuration(ShipmentDuration... args)
    {
        
    }
    
}

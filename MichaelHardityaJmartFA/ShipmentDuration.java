package MichaelHardityaJmartFA;

public class ShipmentDuration{
    public static byte INSTANT = 1;
    public static byte SAME_DAY = 2;
    public static byte NEXT_DAY = 4;
    public static byte REGULER = 8;
    public static byte KARGO = 16;
    private int bit;
    private ShipmentDuration(int bit){
        this.bit = bit;
    }

    public ShipmentDuration(ShipmentDuration... args){
        bit = KARGO ^ SAME_DAY ^ NEXT_DAY ^ REGULER ^ KARGO;
    }
    public int isDuration(ShipmentDuration reference)
    {
        //failed
        return 0;
    }
}

package MichaelHardityaJmartFA;


public class ShipmentDuration
{
    public static final ShipmentDuration INSTANT = new ShipmentDuration(1 << 0);
    public static final ShipmentDuration SAME_DAY = new ShipmentDuration(1 << 1);
    public static final ShipmentDuration NEXT_DAY = new ShipmentDuration(1 << 2);
    public static final ShipmentDuration REGULER = new ShipmentDuration(1 << 3);
    public static final ShipmentDuration KARGO = new ShipmentDuration(1 << 4);
    private int bit;
    private ShipmentDuration(int bit){ //constructor for all things above
        this.bit = bit;
    }
    public ShipmentDuration(ShipmentDuration... args){
        for (ShipmentDuration i:args){
            this.bit |= i.bit;
        }
    }
    public boolean isDuration(ShipmentDuration reference){
        if ((this.bit & reference.bit) == reference.bit){
            return true;
        }
        else{
            return false;
        }
    }
}

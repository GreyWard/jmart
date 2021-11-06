package MichaelHardityaJmartFA;

import java.util.HashMap;

/** menyimpan variabel id dan menjadi parent dari beberapa class lain
*@author MichaelHarditya*/
public class Serializable implements Comparable <Serializable>{
    public final int id;
    private static HashMap<Class<?>,Integer> mapCounter = new HashMap<Class<?>, Integer>();
    protected Serializable(){
    	Integer counter = mapCounter.get(getClass());
    	if(counter == null) {
    		counter = 0;
    	}
    	else{
    		counter++;
    	}
    	this.id = counter;
    	mapCounter.put(getClass(), counter);
    }
    public int compareTo(Serializable other) {
    	return Integer.compare(this.id,other.id);
    }
    public boolean equals(Object other){
         return (other instanceof Serializable && this.id == ((Serializable) other).id);   
    }
    public boolean equals(Serializable other){
            return (this.id == other.id);
    }
    public static <T extends Serializable> int getClosingId (Class<T> clazz) {
    	return mapCounter.get(clazz);
    }
    public static <T extends Serializable> int setClosingId (Class<T> clazz, int id) {
    	mapCounter.put(clazz, id);
    	return 0;
    }
}

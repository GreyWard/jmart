package MichaelHardityaJmartFA;

/** menyimpan variabel id dan menjadi parent dari beberapa class lain
*@author MichaelHarditya*/
public abstract class Recognizable implements Comparable <Recognizable>{
    public final int id;
    protected Recognizable(int id){
        this.id = id;
    }
    public int compareTo(Recognizable other) {
    	return (this.id / other.id);
    }
    public boolean equals(Object other){
         return (other instanceof Recognizable && this.id == ((Recognizable) other).id);   
    }
    public boolean equals(Recognizable other){
            return (this.id == other.id);
    }
    public static <T> int getClosingId (Class<T> clazz) {
    	return 0;
    }
    public static <T> int setClosingId (Class<T> clazz, int id) {
    	return 0;
    }
}

package MichaelHardityaJmartFA;

public class ProductRating
{
    // instance variables - replace the example below with your own
    private long total;
    private long count;

    public ProductRating(){
        this.total = 0;
        this.count = 0;
    }

    public void insert(int rating){
        this.total += rating;
        this.count ++;
    }
    public double getAverage(){
        if (this.count != 0){
            return (this.total/this.count);
        }
        else{
            return 0;
        }
    }
    public long getCount(){
        return this.count;
    }
    public long getTotal(){
        return this.total;
    }
}

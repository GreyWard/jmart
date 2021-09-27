package MichaelHardityaJmartFA;

/** menyimpan variabel id dan menjadi parent dari beberapa class lain
*@author MichaelHarditya*/
public abstract class Recognizable
{
    public final int id;
    protected Recognizable(int id)
    {
        this.id = id;
    }
    public boolean equals(Object test)
    {
         return (test instanceof Recognizable && this.id == ((Recognizable) test).id);   
    }
    public boolean equals(Recognizable test)
    {
            return (this.id == test.id);
    }
}

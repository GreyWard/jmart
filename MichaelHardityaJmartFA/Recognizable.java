package MichaelHardityaJmartFA;


public class Recognizable
{
    // instance variables - replace the example below with your own
    public final int id;
    protected Recognizable(int id)
    {
        this.id = id;
    }

    public boolean equals(Object test)
    {
         return (test instanceof Recognizable && this.id == ((Recognizable) test).id);   
    }
    public boolean equals(Recognizable test){
            return (this.id == test.id);
    }
}

package MichaelHardityaJmartFA;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Store extends Recognizable
{
    public static final String REGEX_NAME = "[A-Z][a-zA-Z0-9]{3,19}[ ]{1}?";
    public static final String REGEX_PHONE = "[0-9]{9,12}";
    public String name;
    public String address;
    public String phoneNumber;
    public Store(int accountId,String name,String address,String phoneNumber)
    {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Store(Account account,String name,String address,String phoneNumber)
    {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    public boolean read(String content){
        return false;
    }
    public String toString()
    {
        return ("name: "+name+"\naddress: "+address+"\nphoneNumber: "+phoneNumber);
    }
    public boolean validate(){
        Pattern patName = Pattern.compile(REGEX_NAME);
        Pattern patPhone = Pattern.compile(REGEX_PHONE);
        Matcher name = patName.matcher(this.name);
        Matcher phone = patPhone.matcher(this.phoneNumber);
        return (name.find() && phone.find());
    }
}

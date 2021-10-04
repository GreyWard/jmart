package MichaelHardityaJmartFA;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Account extends Recognizable implements FileParser
{
    public String name;
    public String email;
    public String password;
    public static final String REGEX_EMAIL = "^(?! |..)[A-Za-z0-9][A-Za-z0-9&_*~]+@[A-Za-z0-9.][A-Za-z0-9.-]+";
    public static final String REGEX_PASSWORD = "^(?! )(?=[A-Za-z0-9])[A-Za-z0-9]{8}$";
    public Account(int id,String name,String email,String password)
    {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public boolean read(String content)
    {
        return false;
    }
    public String toString()
    {
        return ("name: "+name+"\nemail: "+email+"\npassword: "+password);
    }
    public boolean validate(){
        Pattern patMail = Pattern.compile(REGEX_EMAIL);
        Pattern patPass = Pattern.compile(REGEX_PASSWORD);
        Matcher mail = patMail.matcher(this.email);
        Matcher pass = patPass.matcher(this.password);
        return (mail.find() && pass.find());
    }
}

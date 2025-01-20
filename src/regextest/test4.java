package regextest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test4
{
    // https://kshrd.com  https , the " s  " is optional
    public static void main(String[] args) {
        //String regex = "^(https?://([a-zA-Z0-9-]+\\\\.)+[a-z]{0,2})$";
        String regex = "^((https?://)|(www\\.))+([a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}(/\\S*)?$";

        String compareRegex="https://www.kshrd.com";
        Pattern p = Pattern.compile(regex);
        Matcher mt = p.matcher(compareRegex);
            System.out.println("the domain: "+mt.matches());
    }
}

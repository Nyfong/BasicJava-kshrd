package regextest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test2 {
    // apples and apple
    public static void main(String[] args) {
        String regex = "apples?";
        //compare pattern
        String compareParttern = "apple";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(compareParttern);

        System.out.println(m.matches());
    }
}

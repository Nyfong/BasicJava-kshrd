package regextest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test5 {

    // find the match of "user_123";
    public static void main(String[] args) {

        String input = "user_123";

         /*String regex = "^[a-zA-Z0-9_]{3,16}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matchers = pattern.matcher(input);

        if (matchers.matches()) {
            System.out.println("True");
        }else {
            System.out.println("False");
        }
        */

        //shortHand
        boolean validation = Pattern.matches("^[a-zA-Z0-9_]{3,16}$",input);
        System.out.println((validation)? "matched": "not matched");
    }
}

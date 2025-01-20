package regextest;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test3 {
    // match the 123-123-123
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Pattern p = Pattern.compile("(\\d{3}\\D){2}");
        String s = "123-123-";
        boolean isMatcher = false;
            Matcher m = p.matcher(s);
            if(m.find()) isMatcher = true;
        System.out.println("isMatcher: " + isMatcher);
    }
}

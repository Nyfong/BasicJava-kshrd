// Java program to demonstrate working of
// String matching in Java

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class test {
    public static void main(String args[])
    {

        // Create a pattern to be searched
        // Custom pattern
//        Pattern p = Pattern.compile("[^a-z]");
        Pattern p = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"); // for email
        String compare = "test@gmail.com";

        Matcher m = p.matcher(compare);

        while (m.find()) System.out.print("" + m.group(0) +", ");


        // Finding string using find() method
//        while (m.find())
//
//            // Print starting and ending indexes
//            // of the pattern in the text
//            // using this functionality of this class
//            System.out.println("Pattern found from "
//                    + m.start() + " to "
//                    + (m.end() - 1));

    }
}

/**
 *       a-zA-Z0-9 - Alphanumeric characters.
 *       ._%+- - Allows dots (.), underscores (_), percent signs (%), plus signs (+), and hyphens (-).
 *      + - Ensures at least one character is present.
 *      ^ - Asserts the start of the string.
 *
 *      [a-zA-Z]{2,} - Matches the TLD:
 *
 *      a-zA-Z - Only letters are allowed in the TLD.
 *
 *      {2,} - Ensures the TLD is at least 2 characters long (e.g., .com, .org).
 *
 *      $ - Asserts the end of the string.
 *
 *
 *           user@example.com (valid)
 *
 *           user.name+tag+sorting@example.com (valid)
 *
 *          user@sub.example.com (valid)
 *
 *          user@123.123.123.123 (valid in some cases)
 *
 *          user@[IPv6:2001:db8::1] (valid but not covered by this regex)
 *
 *          invalid-email@ (invalid)
 *
 *          invalid-email@.com (invalid)
 * */
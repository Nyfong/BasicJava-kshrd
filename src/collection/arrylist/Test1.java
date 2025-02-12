package collection.arrylist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test1 {
    //Write a program to sort an ArrayList of Strings alphabetically.
    public static void main(String[] args) {

        List<String> myList = new ArrayList<>();
        myList.add("c");
        myList.add("f");
        myList.add("a");
        myList.add("z");
        Collections.sort(myList);
        System.out.println(myList);



    }
}

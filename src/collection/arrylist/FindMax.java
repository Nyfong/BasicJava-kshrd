package collection.arrylist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FindMax {
    public static void main(String[] args) {


        List<Integer> numList = new ArrayList<>(
                Arrays.asList(1, 2, 3, 40, 5, 6, 7, 10, 20)
        );
        int maximum = Collections.max(numList);
        System.out.println(numList);
        System.out.println(maximum);


    }
}

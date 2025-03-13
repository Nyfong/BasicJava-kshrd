package javaapi;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class FindFirst {
    public static void main(String[] args) {
        createStream_whenFindFirstResultIsPresent_thenCorrect();
    }

    public static void createStream_whenFindFirstResultIsPresent_thenCorrect() {

        List<String> list = Arrays.asList("A", "B", "C", "D");

        Optional<String> result = list.stream().findAny();

        System.out.println(result.isPresent());
        System.out.println(result.get());

    }
}

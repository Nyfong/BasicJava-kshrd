package java8.optional;

import java.util.Optional;

public class Test2 {
    public static void main(String[] args) {
        String[] words = new String[5];
        words[0] = "Hello";
        words[1] = "Optional";
        words[2] = "Class";
        words[3] = "in";
        words[4] = "Java 8";

        Optional<String> optionalWord = Optional.ofNullable(words[2]);
        if (optionalWord.isPresent()) {
            String word = optionalWord.get();
            System.out.println("The word is: " + word);
        } else {
            System.out.println("No word found.");
        }

        Optional<String> emptyOptional = Optional.ofNullable(words[5]);
        emptyOptional.ifPresent(word -> System.out.println("The word is: " + word));
    }
}
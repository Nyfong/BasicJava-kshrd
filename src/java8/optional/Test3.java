package java8.optional;
import  java.util.Optional;
public class Test3 {
    public static void main(String[] args) {

        Optional<Integer> optionalInt = Optional.of(10);
        Optional<Integer> filteredOptional = optionalInt.filter(value -> value > 5);
        System.out.println(filteredOptional.isPresent()); // true
    }
}

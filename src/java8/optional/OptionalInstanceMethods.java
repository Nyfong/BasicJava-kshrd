package java8.optional;

// Java program to demonstrate
// the above method

import java.util.Optional;

public class OptionalInstanceMethods {
    public static void main(String[] args) {
        // Creating an Optional instance with a value
        Optional<String> optionalValue = Optional.of("Hello, Java 8!");

        // Using isPresent()
        if (optionalValue.isPresent()) {
            System.out.println("Value is present: " + optionalValue.get());
        }

        // Using ifPresent()
        optionalValue.ifPresent(value -> System.out.println("Using ifPresent: " + value));

        // Using orElse()
        String result = optionalValue.orElse("Default Value");
        System.out.println("Using orElse: " + result);

        // Using orElseGet()
        String resultWithSupplier = optionalValue.orElseGet(() -> "Generated Default Value");
        System.out.println("Using orElseGet: " + resultWithSupplier);

        // Using orElseThrow()
        try {
            String value = optionalValue.orElseThrow(() -> new RuntimeException("Value not found!"));
            System.out.println("Using orElseThrow: " + value);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Using map()
        Optional<Integer> length = optionalValue.map(String::length);
        System.out.println("Using map (String length): " + length.orElse(0));

        // Using filter()
        Optional<String> filteredValue = optionalValue.filter(val -> val.startsWith("Hello"));
        System.out.println("Using filter: " + filteredValue.orElse("No match"));

        // Using flatMap()
        Optional<Optional<String>> nestedOptional = Optional.of(Optional.of("Nested Value"));
        Optional<String> flatMappedValue = nestedOptional.flatMap(opt -> opt);
        System.out.println("Using flatMap: " + flatMappedValue.orElse("No value"));
    }
}

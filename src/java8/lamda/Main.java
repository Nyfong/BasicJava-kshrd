package java8.lamda;

@FunctionalInterface
interface MyFunctionalInterface {
    void myMethod();  // Single abstract method

    // default method (optional)
    default void defaultMethod() {
        System.out.println("Default method in functional interface");
    }

    // static method (optional)
    static void staticMethod() {
        System.out.println("Static method in functional interface");
    }
}

public class Main {
    public static void main(String[] args) {
        // Using Lambda Expression
        MyFunctionalInterface obj = () -> System.out.println("Lambda Expression Implementing myMethod()");
        obj.myMethod();  // Output: Lambda Expression Implementing myMethod()

        // Using default and static methods
        obj.defaultMethod();  // Output: Default method in functional interface
        MyFunctionalInterface.staticMethod();  // Output: Static method in functional interface
    }
}

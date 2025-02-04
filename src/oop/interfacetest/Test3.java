package oop.interfacetest;


class MyClass implements MyInterface {
    @Override
    public void regularMethod(String message) {
        System.out.println("MyClass implementation: " + message);
    }

    // Can override the default method for a custom implementation
    @Override
    public String defaultMethod(int number) {
        return "MyClass custom implementation: " + number * 3;
    }

    // Cannot override the static method.
}

public class Test3 {
    public static void main(String[] args) {
        MyClass myObject = new MyClass();

        // Call the regular method (must be implemented by the class)
        myObject.regularMethod("Hello from MyClass!");

        // Call the default method (can be overridden, but has a default implementation)
        System.out.println(myObject.defaultMethod(5)); // Uses the overridden method

        MyInterface myInterface = new MyClass(); // Polymorphism
        System.out.println(myInterface.defaultMethod(10)); // Uses the overridden method

        // Call the static method (call it on the interface, not an instance)
        int sum = MyInterface.staticMethod(7, 3);
        System.out.println("Sum: " + sum);
    }
}
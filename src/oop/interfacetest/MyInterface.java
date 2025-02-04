package oop.interfacetest;

public interface MyInterface {
    int a  =0;
    // Regular abstract method (must be implemented)
    void regularMethod(String message);
    // Default method (provides a default implementation)
    public default String defaultMethod(int number) {
        return "Default implementation: " + number * 2;
    }

    // Static method (associated with the interface itself)
    static int staticMethod(int a, int b) {
        return a + b;
    }
}

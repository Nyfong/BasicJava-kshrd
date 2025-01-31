package oop.poly;

class MathOperations {
    // Method to add two integers
    public int add(int a, int b) {
        return a + b;
    }

    // Method to add three integers
    public int add(int a, int b, int c) {
        return a + b + c;
    }

    // Method to add two double values
    public double add(double a, double b) {
        return a + b;
    }
}

public class Main {
    public static void main(String[] args) {
        MathOperations mathOps = new MathOperations();
        System.out.println(mathOps.add(2, 3));           // Calls add(int, int)
        System.out.println(mathOps.add(2, 3, 4));        // Calls add(int, int, int)
        System.out.println(mathOps.add(2.5, 3.5));       // Calls add(double, double)
    }
}


package exception;

public class Test2 {
    public static int testMethod() {
        try {
            System.out.println("Inside try block");
            int result = 10;
            return 10;
        } catch (ArithmeticException e) {
            System.out.println("Inside catch block: " + e);
            return 15;
        } finally {
            System.out.println("Inside finally block");
            return 20;  // Finally overrides the previous return statements
        }
    }

    public static void main(String[] args) {
        int result = testMethod();
        System.out.println("Returned value: " + result);
    }
}

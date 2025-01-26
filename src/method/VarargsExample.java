package method;

public class VarargsExample {

    public static void main(String[] args) {
        printNumbers(1, 2, 3, 4, 5);
        printNumbers(10, 20);
        printNumbers(); // No arguments
    }

    public static void printNumbers(int... numbers) {
        System.out.print("Numbers: ");
        for (int number : numbers) {
            System.out.print(number + " ");
        }
        System.out.println();
    }
}
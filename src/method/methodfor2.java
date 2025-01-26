package method;

public class methodfor2 {
    public static
    void printNumbers(int... numbers) {
        System.out.print("Numbers: ");
        for (int number : numbers) {
            System.out.print(number + " ");
        }
        System.out.println();
    }

    public static final int apple = 0;
}

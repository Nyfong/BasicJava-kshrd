package thread;
public class SleepExample {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting...");
        Thread.sleep(2000); // Sleep for 2 seconds
        System.out.println("Waking up...");

        for (int i = 0; i < 5; i++) {
            System.out.println("Iteration: " + i);
            Thread.sleep(500); // Sleep for 500 milliseconds
        }
    }
}

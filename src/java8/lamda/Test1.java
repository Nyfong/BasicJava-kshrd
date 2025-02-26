package java8.lamda;

public class Test1 {
    public static void main(String[] args) {

        Runnable runnable = () ->{
            System.out.println("This is the run method ");
        };

        runnable.run();


    }
}

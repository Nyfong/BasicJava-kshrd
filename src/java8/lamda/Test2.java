package java8.lamda;
@FunctionalInterface
interface Greeting{
    void hello();
}
public class Test2 {
    public static void main(String[] args) {

        Greeting greeting = ()->{
            System.out.println("impliment the hello");
        };
        greeting.hello();

    }
}

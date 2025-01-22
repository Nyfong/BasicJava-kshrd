package controlflow.decision;

public class Decision2 {
    public static void main(String[] args) {

        enum Colors {
            RED;
        }
        Colors color = Colors.RED;
        if (color == Colors.RED) System.out.println("Mathced");
    }
}

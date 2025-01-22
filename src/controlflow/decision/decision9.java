package controlflow.decision;

public class decision9 {
    public static void main(String[] args) {

        int a = 10;
        String v = "10";
        System.out.println(a+v);
        String where = switch (a) {
            // line n1
            case 10 -> {
                yield "hold";
            } // line n2
            case 20 -> {
                yield "down";
            }       // line n3
            default ->
//                throw new IllegalStateException("Invalid day: " + dir);
                    "invalid";


        };
        System.out.print(where);
    }
}

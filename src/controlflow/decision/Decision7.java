package controlflow.decision;

public class Decision7 {
    public static void main(String[] args) {

        enum Direction {
            UP, DOWN, HOLD;
        }
        Direction dir = Direction.UP;
        String where = switch (dir) {
            // line n1
            case HOLD -> {
                yield "hold";
            } // line n2
            case DOWN -> {
                yield "down";
            }       // line n3
            default ->
//                throw new IllegalStateException("Invalid day: " + dir);
                "invalid";


        };
        System.out.print(where);
    }
}

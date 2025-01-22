package controlflow.decision;

public class Decision5 {
    public static void main(String[] args) {
        /**
         *
         *  if(Boolean_expression 1) {
         *             // Executes when the Boolean expression 1 is true
         *             if(Boolean_expression 2) {
         *                 // Executes when the Boolean expression 2 is true
         *             }
         *         }
         */
        int x = 30;
        int y = 10;

        if (x == 30) {
            if (y == 10) {
                System.out.print("X = 30 and Y = 10");
            }
        }


    }
}
//if (x == 30 && y ==10) System.out.print("X = 30 and Y = 10");
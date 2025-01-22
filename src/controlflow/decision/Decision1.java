package controlflow.decision;

public class Decision1 {
    //  taking about if statement
    public static void main(String[] args) {

        enum MATH {
            PI(34);
            private  final int i ;
            MATH(int i){
                this.i = i;
            }
        }
        MATH math = MATH.PI;

        if(math == MATH.PI) System.out.println("it is 3.14");
    }

}

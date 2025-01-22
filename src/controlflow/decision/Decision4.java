package controlflow.decision;

public class Decision4 {
    public static void main(String[] args) {



        int testscore = 76;
        char grade;

        if (testscore >= 90) {
            grade = 'A';
        } else if (testscore >= 80) {
            grade = 'B';
        } else if (testscore >= 70) {
            grade = 'C';
        } else if (testscore >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }
        System.out.println("Grade = " + grade);
    }
}
//        int x = 30;
//
//        if( x < 20 ) {
//            System.out.print("This is if statement");
//        }else {
//            System.out.print("This is else statement");
//        }
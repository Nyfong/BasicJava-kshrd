package collection.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Iterator;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input;
        String GREEN = "\u001B[32m", RED = "\u001B[31m", RESET = "\u001B[30m";
        int userInput;
        boolean condition = true;
        String gender[] = {"M", "F"};

        String[] result = new String[70];

        // create instance of Random class
        Random rand = new Random();
        //1. Create an ArrayList<Student> to store student objects.
        List<Student> studentObject = new ArrayList<>();
        //2. Prompt the user to enter the number of students.
        do {
            System.out.print("Enter the number of students -> : ");
            input = sc.nextLine();
            if (input.matches("\\d+")) {
                userInput = Integer.parseInt(input);
                for (int i = 1; i <= userInput; i++) {
                    // Generate random integers age in range 0 to 50
                    int rand_int1 = rand.nextInt(50);
                    //random gender
                    result[i]=gender[rand.nextInt(gender.length)];
                    //3. Use a loop to collect student details (ID, Name, Age, Gender) follow by number of students
                    Student Stu1 = new Student((i), "Aman", rand_int1, result[i]);
                    studentObject.add(Stu1);
                    condition = true;
                }
            } else {
                System.out.println(RED + "Enter only degit" + RESET);
                condition = false;
            }
        } while (!condition);

        // Using the hasNext() method
        //4. Print all students.
        System.out.println("Print all Student ");
        for (Student c : studentObject)
            System.out.println(c);

        Iterator<Student> iterator = studentObject.iterator();
        //5. Count students by gender and print results.
        int m =0 , f =0;
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getGender().equalsIgnoreCase("M")) m+=1;
            else f+=1;
        }
        System.out.println("5. Male = "+m +", Female =" +f);
        // studentObject.removeIf(c -> c.getAge() < 20);
        // 6. Remove students that have age under 20

        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getAge() < 20) iterator.remove();
        }
        // 7. Print the final list of students.
        System.out.println("Print age Student under 20 ");
        for (Student c : studentObject) System.out.println(c);

    }
}

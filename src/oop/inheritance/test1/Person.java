package oop.inheritance.test1;

public class Person {
    public String name;
    public String person = "person";
    public int age;

    //cons
    public Person(String name, int age)
    {
        this.name = name;
        this.age = age;
    }
    //methods

    public  void personGreeting()
    {
        System.out.println("Hello "  + this.person );
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", person='" + person + '\'' +
                ", age=" + age +
                '}';
    }
}

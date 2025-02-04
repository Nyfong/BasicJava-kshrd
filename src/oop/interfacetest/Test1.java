package oop.interfacetest;

interface A {
    void printHello();
}
interface B {
    void printHi();
}

class Greeting implements A , B{
    @Override
    public void printHello() {
        System.out.println("hello");
    }
   @Override
    public void printHi(){
       System.out.println("hi");
   }



}

public class Test1 {
    public static void main(String[] args) {
        Greeting obj = new Greeting();
        obj.printHello();
    }
}

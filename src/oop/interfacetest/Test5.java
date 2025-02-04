package oop.interfacetest;

interface If {
    int x = 10; // Is this a variable declaration?
}

class Test5 implements If {
    public static void main(String[] args) {

        System.out.println(If.x); // How do you access x?
         // Can you change x?
    }
}
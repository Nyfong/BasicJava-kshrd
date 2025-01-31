package oop.encap;
class AP{
    private final int a =1; //  data hiding

}

public class Encap1 {


    public static void main(String[] args) {
        AP m = new AP();
        System.out.println("trying to call ap private" + m); // can not access control to a
    }
}
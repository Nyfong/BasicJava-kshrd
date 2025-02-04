package oop.interfacetest;

// Interface 1
interface Printable {
    void print();
}

// Interface 2
interface Drawable {
    void draw();
}

// Abstract Class (can be extended)
abstract class Shape {
    protected String color;

    public Shape(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public abstract double getArea(); // Abstract method - subclasses must implement
}

// Class extending Shape and implementing both interfaces
class Rectangle extends Shape implements Printable, Drawable {
    private double length;
    private double width;

    public Rectangle(String color, double length, double width) {
        super(color); // Call the superclass constructor
        this.length = length;
        this.width = width;
    }

    @Override
    public void print() {
        System.out.println("Printing a rectangle (color: " + color + ", area: " + getArea() + ")");
    }

    @Override
    public void draw() {
        System.out.println("Drawing a rectangle...");
    }

    @Override
    public double getArea() {
        return length * width;
    }
}

public class Test4 {
    public static void main(String[] args) {
        Rectangle myRectangle = new Rectangle("blue", 5, 10);

        myRectangle.print(); // From Printable interface
        myRectangle.draw(); // From Drawable interface
        System.out.println("Rectangle color: " + myRectangle.getColor()); // From Shape class
        System.out.println("Rectangle area: " + myRectangle.getArea()); // From Shape class (implemented in Rectangle)

        // Polymorphism: Using interface type
        Printable p = myRectangle;
        p.print();

        Drawable d = myRectangle;
        d.draw();

        Shape s = myRectangle; // Polymorphism with abstract class
        System.out.println("Shape area: " + s.getArea()); // Calls the Rectangle's getArea()
        System.out.println("Shape color: " + s.getColor()); // Calls the Shape class getColor()
    }
}

/*
    * Printable p; // p can hold a reference to *any* Printable object

    p = myRectangle; // p now refers to a Rectangle
    p.print();      // Calls the Rectangle's print() method

    Circle myCircle = new Circle(); // Assume Circle implements Printable
    p = myCircle;    // p now refers to a Circle
    p.print();      // Calls the Circle's print() method

     Printable p: This declares a variable named p of type Printable.  Printable is an interface.  Remember, an interface defines a contract (a set of methods) but doesn't provide any implementation.  p is a reference variable that can hold a reference to any object that implements the Printable interface.

    myRectangle: This is an object of type Rectangle.  Crucially, the Rectangle class implements the Printable interface.  This means that Rectangle promises to provide an implementation for all the methods defined in the Printable interface (in this case, the print() method).

    = myRectangle;: This is an assignment.  It assigns the reference of the myRectangle object to the variable p.
* */
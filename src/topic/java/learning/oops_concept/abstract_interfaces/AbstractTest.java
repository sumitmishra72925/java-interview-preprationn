package topic.java.learning.oops_concept.abstract_interfaces;

public class AbstractTest {

    public static void main(String[] args) {
        Shape circle = new Circle(2.5);
        System.out.printf("Area of circle is: %.2f", circle.calculateArea());
        System.out.println();

        Shape rectangle = new Rectangle(4, 6);
        System.out.printf("Area of rectangle is: %.2f",rectangle.calculateArea());


        System.out.println();
        Bird eagle = new Eagle();
        eagle.fly();
        eagle.makeSound();
        System.out.println("Name of the bird is: "+eagle.name);
        System.out.println("Color of bird is: "+eagle.color);
        System.out.println(eagle.name+" can fly up to "+eagle.maxAltitude+" mts.");
    }
}

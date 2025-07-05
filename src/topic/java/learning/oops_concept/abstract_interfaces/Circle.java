package topic.java.learning.oops_concept.abstract_interfaces;

public class Circle extends Shape {

    private double radius;

    public Circle(double radius){
        this(Circle.class.getName(), "1-D");
        this.radius = radius;
    }

    public Circle(String name, String dimension) {
        super(name, dimension);
    }

    @Override
    double calculateArea() {
        if(this.radius <= 0){
            throw new RuntimeException("Invalid radius!");
        }
        return 2 * Math.PI * Math.pow(this.radius, 2) ;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}

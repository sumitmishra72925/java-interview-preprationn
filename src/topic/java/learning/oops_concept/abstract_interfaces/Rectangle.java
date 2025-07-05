package topic.java.learning.oops_concept.abstract_interfaces;

public class Rectangle extends Shape{

    private double length;
    private double width;


    public Rectangle(double length, double width) {
        super(Rectangle.class.getName(), "1-D");
        this.length = length;
        this.width = width;
    }

    public Rectangle(String name, String dimension) {
        super(name, dimension);
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    double calculateArea() {
        if(length == 0 || width == 0){
            throw new RuntimeException("Invalid sides!");
        }
        return length * width;
    }
}

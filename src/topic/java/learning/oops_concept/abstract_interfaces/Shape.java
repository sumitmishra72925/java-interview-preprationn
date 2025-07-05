package topic.java.learning.oops_concept.abstract_interfaces;

public abstract class Shape {

    protected String name;
    protected String dimension;

    public Shape(String name, String dimension) {
        this.name = name;
        this.dimension = dimension;
    }

    abstract double calculateArea();
}

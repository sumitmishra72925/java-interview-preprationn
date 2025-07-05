package topic.java.learning.oops_concept.abstract_interfaces;

public abstract class Bird implements Flyable {
    protected String name;
    protected String color;
    protected double maxAltitude;

    public Bird(String name, String color, double maxAltitude) {
        this.name = name;
        this.color = color;
        this.maxAltitude = maxAltitude;
    }

    abstract void makeSound();
}

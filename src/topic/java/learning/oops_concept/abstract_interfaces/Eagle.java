package topic.java.learning.oops_concept.abstract_interfaces;

public class Eagle extends Bird{

    public Eagle(){
        this("Eagle", "Yellowish", 100);
    }


    public Eagle(String name, String color, double maxAltitude) {
        super(name, color, maxAltitude);
    }

    @Override
    void makeSound() {
        System.out.println("Eagle is saying Kwish, Kwish");
    }

    @Override
    public void fly() {
        System.out.println("Eagle is flying high in the sky.");
    }
}

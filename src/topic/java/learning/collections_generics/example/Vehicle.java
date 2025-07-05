package topic.java.learning.collections_generics.example;

public abstract class Vehicle {
    protected String brand;
    protected String year;
    protected String color;

    public Vehicle(String brand, String year, String color) {
        this.brand = brand;
        this.year = year;
        this.color = color;
    }

    public  abstract String getType();

    public  abstract void startVehicle();
}

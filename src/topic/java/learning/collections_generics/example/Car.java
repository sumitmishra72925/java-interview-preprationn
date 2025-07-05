package topic.java.learning.collections_generics.example;

public class Car extends Vehicle{


    public Car(String brand, String year, String color) {
        super(brand, year, color);
    }

    @Override
    public void startVehicle() {
        System.out.println(this.brand+ " is starting, Vroom, Vroom...");
    }

    @Override
    public String getType() {
        return Car.class.getSimpleName();
    }
}

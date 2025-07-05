package topic.java.learning.collections_generics.example;

public class MotorCycle extends Vehicle{
    public MotorCycle(String brand, String year, String color) {
        super(brand, year, color);
    }

    @Override
    public void startVehicle() {
        System.out.println(this.brand + "is starting...");


    }

    @Override
    public String getType() {
        return MotorCycle.class.getSimpleName();
    }
}

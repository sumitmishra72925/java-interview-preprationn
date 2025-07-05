package topic.java.learning.collections_generics.example;

public class TestDriver {


    public static void main(String[] args) {

        Garage<Car> carGarage = new Garage<>();

        carGarage.addVehiclesToGarage(new Car("BMW", "2021", "Black"));
        carGarage.addVehiclesToGarage(new Car("Maruti", "1976", "White"));
        carGarage.addVehiclesToGarage(new Car("Hundai", "2001", "Red"));

        Garage<MotorCycle> motorCycleGarage = new Garage<>();
        motorCycleGarage.addVehiclesToGarage(new MotorCycle("Hero", "1990", "Blue"));
        motorCycleGarage.addVehiclesToGarage(new MotorCycle("Bullet", "1995", "Light Yellow"));


        System.out.println("=====Car garage information=======");
        System.out.printf("Total %d cars are available in garage\n", carGarage.getTotalVehicles());
        carGarage.printVehicles();
        carGarage.startAllVehicles(carGarage.getVehicles());


        System.out.println("=====Motorcycle garage information=======");
        System.out.printf("Total %d cars are available in garage\n", motorCycleGarage.getTotalVehicles());
        motorCycleGarage.printVehicles();
        motorCycleGarage.startAllVehicles(motorCycleGarage.getVehicles());








    }
}

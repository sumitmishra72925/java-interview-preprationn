package topic.java.learning.collections_generics.example;

import java.util.ArrayList;
import java.util.List;

public class Garage <T extends Vehicle>{

    private List<T> vehicles;


    public void printVehicles(){
        for (T vehicle : vehicles) {
            System.out.println("=====Vehicle information======");
            System.out.println("Type: "+vehicle.getType());
            System.out.printf("Name: %s, Year: %s, Color: %s \n", vehicle.brand, vehicle.year, vehicle.color );
        }
    }

    public void startAllVehicles(List<? extends Vehicle> list){
        for (Vehicle vehicle : list) {
            vehicle.startVehicle();
        }
    }


    public int getTotalVehicles(){
        return vehicles.size();
    }

    public void addVehiclesToGarage(T vehicle){
        if(this.vehicles == null)
            vehicles = new ArrayList<>();

        System.out.println("Added "+vehicle.brand+" to garage. ");
        this.vehicles.add(vehicle);
    }

    public List<T> getVehicles(){
        //returns copy for safety
        return new ArrayList<>(this.vehicles);
    }

}

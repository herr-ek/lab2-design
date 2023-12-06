package lab2.Factories;

import lab1.Saab95;
import lab1.Vehicle;

public class SaabFactory extends VehicleFactory{

    public Vehicle createVehicle() {
        return new Saab95();
    }
}

package lab2.Factories;

import lab1.Vehicle;
import lab2.Scania;

public class ScaniaFactory extends VehicleFactory{

    public Vehicle createVehicle() {
        return new Scania();
    }
}

package lab2.Factories;

import lab1.Vehicle;
import lab2.Scania;

public class ScaniaFactory implements IVehicleFactory {

    public Vehicle createVehicle() {
        return new Scania();
    }
}

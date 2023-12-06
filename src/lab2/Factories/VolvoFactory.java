package lab2.Factories;

import lab1.Vehicle;
import lab1.Volvo240;

public class VolvoFactory extends VehicleFactory{

    public Vehicle createVehicle() {
        return new Volvo240();
    }
}

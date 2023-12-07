package vehicles.factories;

import vehicles.base.Vehicle;
import vehicles.loaders.Scania;

public class ScaniaFactory implements IVehicleFactory {

    public Vehicle createVehicle() {
        return new Scania();
    }
}

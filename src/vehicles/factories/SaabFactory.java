package vehicles.factories;

import vehicles.base.Saab95;
import vehicles.base.Vehicle;

public class SaabFactory implements IVehicleFactory {

    public Vehicle createVehicle() {
        return new Saab95();
    }
}

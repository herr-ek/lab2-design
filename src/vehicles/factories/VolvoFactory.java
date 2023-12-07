package vehicles.factories;

import vehicles.base.Vehicle;
import vehicles.base.Volvo240;

public class VolvoFactory implements IVehicleFactory {

    public Vehicle createVehicle() {
        return new Volvo240();
    }
}

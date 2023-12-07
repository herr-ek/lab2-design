package vehicles.loaders;
import vehicles.base.Vehicle;
import vehicles.loaders.TruckBeds.*;

public abstract class Truck extends Vehicle {

    AbstractBed truckbed;

    public boolean isNotMoving() {
        return currentSpeed == 0;
    }

    @Override
    public void move(){
        if (truckbed.isTruckBedSecured())
            super.move();
    }

    public void modifyCurrentWeight(int currentWeight){
        truckbed.modifyCurrentWeight(currentWeight);
    }

    public void raiseTruckBed() {
        if (isNotMoving())
            truckbed.raiseTruckBed();
    }

    public void lowerTruckBed() {
        if (isNotMoving())
            truckbed.lowerTruckBed();
    }

}

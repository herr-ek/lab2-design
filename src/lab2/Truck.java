package lab2;
import lab1.Vehicle;
import lab2.TruckBeds.*;

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

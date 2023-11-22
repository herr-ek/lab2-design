package lab2;

import lab1.Vehicle;

public class TruckBedTruck extends Vehicle {

    Truckbed truckbed;

    public TruckBedTruck(int maxLoadWeight, int maxAngle) {
        truckbed = new Truckbed(maxLoadWeight, maxAngle);
    }

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

    public void raiseTruckBed(boolean complete) {
        if (isNotMoving())
            truckbed.raiseTruckBed(complete);
    }

    public void raiseTruckBed(int angle) {
        if (isNotMoving())
            truckbed.raiseTruckBed(angle);
    }

    public void lowerTruckBed(boolean complete) {
        if (isNotMoving())
            truckbed.lowerTruckBed(complete);
    }

    public void lowerTruckBed(int angle) {
        if (isNotMoving())
            truckbed.lowerTruckBed(angle);
    }
}

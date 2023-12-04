package lab2.TruckBeds;

public abstract class AbstractBed implements ITruckBed{

    protected int maxLoadWeight;
    protected int currentLoadWeight = 0;

    // The currentAngle and maxAngle of the Bed is kept in the concrete subclasses to allow for more extensibility with Beds that don't have angles

    public int getMaxLoadWeight() {
        return maxLoadWeight;
    }

    public int getCurrentLoadWeight() {
        return currentLoadWeight;
    }

    public void modifyCurrentWeight(int weightDiff) {
        currentLoadWeight += weightDiff;
    }

    public boolean isTruckBedSecured() {
        return getCurrentAngle() == 0;
    }

}

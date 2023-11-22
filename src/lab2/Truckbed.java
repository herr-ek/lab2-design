package lab2;

public class Truckbed {

    private int currentAngle;
    private final int maxAngle;
    private final int maxLoadWeight;
    private int currentLoadWeight;

    public Truckbed(int maxLoadWeight, int maxAngle) {
        this.currentAngle = 0;
        this.maxLoadWeight = maxLoadWeight;
        this.maxAngle = maxAngle;
        currentLoadWeight = 0;
    }

    public boolean isTruckBedSecured() {
        return getCurrentAngle() == 0;
    }

    public int getCurrentAngle() {
        return currentAngle;
    }

    public int getMaxLoadWeight() {
        return maxLoadWeight;
    }

    public int getCurrentLoadWeight() {return currentLoadWeight;}

    public void modifyCurrentWeight(int currentWeight){
        this.currentLoadWeight += currentWeight;
    }

    public void raiseTruckBed(boolean complete) {
        if (complete) currentAngle = maxAngle;
    }


    public void raiseTruckBed(int angle){
        if (currentAngle + angle > maxAngle)
            currentAngle = maxAngle;
        else
            currentAngle += angle;
    }

    public void lowerTruckBed(boolean complete) { if (complete) currentAngle = 0;
    }

    public void lowerTruckBed(int angle) {
            if (currentAngle - angle < 0)
                currentAngle = 0;
            else
                currentAngle -= angle;
        }
    }



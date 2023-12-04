package lab2.TruckBeds;

public class ScaniaTruckBed extends AbstractBed{

    private int currentAngle = 0;
    private final int maxAngle;

    public ScaniaTruckBed(int maxLoadWeight) {
        this.maxLoadWeight = maxLoadWeight;
        maxAngle = 70;
    }


    public void raiseTruckBed(){
        if (currentAngle + 10 > maxAngle)
            currentAngle = maxAngle;
        else
            currentAngle += 10;
    }

    public void lowerTruckBed() {
        if (currentAngle - 10 < 0)
            currentAngle = 0;
        else
            currentAngle -= 10;
    }

    public int getCurrentAngle() {
        return currentAngle;
    }

    public int getCurrentLoadWeight() {
        return 0;
    }


}

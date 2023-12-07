package vehicles.loaders.TruckBeds;

public interface ITruckBed {

    void raiseTruckBed();
    void lowerTruckBed();
    boolean isTruckBedSecured();
    int getCurrentAngle();
    int getMaxLoadWeight();
    int getCurrentLoadWeight();
    void modifyCurrentWeight(int weightDiff);
}

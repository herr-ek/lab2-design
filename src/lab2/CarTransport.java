package lab2;
import lab1.Position;

import java.awt.*;

public class CarTransport extends TruckBedTruck implements ILoader {

    private final LoadCarrier<Loadable> loadCarrier;
    private final int maxUnitWeight;

    public CarTransport(int maxLoadWeight, int maxUnits) {
        super(maxLoadWeight, 1);
        loadCarrier = new LoadCarrier<>(maxUnits);
        nrDoors = 2;
        enginePower = 700;
        color = Color.darkGray;
        modelName = "Scania T144";
        weight = 14000;
        maxUnitWeight = 3000;
    }

    public LoadCarrier<Loadable> getLoadCarrier() {return loadCarrier;}

    public boolean isLoadable() {
        return truckbed.getCurrentAngle() == 1 && isNotMoving();
    }

    private boolean checkLoadRestrictions(Loadable vehicle){
        return (vehicle.getWeight() + truckbed.getCurrentLoadWeight() <= truckbed.getMaxLoadWeight() && vehicle.getWeight() <= maxUnitWeight && loadCarrier.sizeOfCargo() < loadCarrier.getMaxUnits());
    }

    private boolean checkDistanceToLoad(Loadable vehicle){
        return getPosition().absDistance(vehicle.getPosition()) <= 5.0;
    }

    public void load(Loadable vehicle) throws LoaderException {
        if (isLoadable() && checkLoadRestrictions(vehicle) && checkDistanceToLoad(vehicle)) {
            loadCarrier.load(vehicle);
            modifyCurrentWeight(vehicle.getWeight());
        } else {
            throw new LoaderException("Transport is not loadable or the vehicle you are trying to load exceeds the limits or is too far away");
        }
    }

    public Loadable unload(Loadable vehicle) throws LoaderException {
        throw new LoaderException("Unsupported unload");
    }

    public Loadable unload() throws LoaderException {
        if (isLoadable()) {
            Loadable vehicle = loadCarrier.unloadLast();
            modifyCurrentWeight(-vehicle.getWeight());
            vehicle.getPosition().setX(vehicle.getPosition().getX()-5.0);
            return vehicle;
        } else {
            throw new LoaderException("Transport is not unloadable or has no cars");
        }
    }


    @Override
    public void move(){
        super.move();
        Position newPosition = getPosition();
        int newDirection = getDirection();
        for (Loadable v : loadCarrier.getCargo()){
            v.getPosition().setX(newPosition.getX());
            v.getPosition().setY(newPosition.getY());
            v.setDirection(newDirection);
        }
    }

    @Override
    public void turnLeft(){
        super.turnLeft();
        int newDirection = getDirection();
        for (Loadable v : loadCarrier.getCargo()){
            v.setDirection(newDirection);
        }
    }

    @Override
    public void turnRight(){
        super.turnRight();
        int newDirection = getDirection();
        for (Loadable v : loadCarrier.getCargo()){
            v.setDirection(newDirection);
        }
    }
}


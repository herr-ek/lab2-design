package graphics;

import vehicles.base.Saab95;
import vehicles.base.Vehicle;
import vehicles.factories.SaabFactory;
import vehicles.factories.ScaniaFactory;
import vehicles.factories.IVehicleFactory;
import vehicles.factories.VolvoFactory;
import vehicles.loaders.Scania;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Random;

// Should handle all functions that affect the model. A middle-man so to say.
public class CarModelFacade {

    LinkedList<Vehicle> cars = new LinkedList<>();
    LinkedList<VehicleObserver> observers = new LinkedList<>();
    int maxCars;

    public CarModelFacade(int maxCars) {
        this.maxCars = maxCars;
    }

    public void fillWithCars() throws ControllerException {
        addNewVehicle(new VolvoFactory());
        addNewVehicle(new SaabFactory());
        addNewVehicle(new ScaniaFactory());
    }

    void removeLastVehicle() throws ControllerException {
        if (!cars.isEmpty()) {
            notifyAllObservers(UpdateEvent.CARREMOVED);
            cars.removeLast();
        }else throw new ControllerException("No cars left!");
    }

    void addNewVehicle(IVehicleFactory factory) throws ControllerException {
        if (cars.size() < maxCars) {
            cars.add(factory.createVehicle());
            notifyAllObservers(UpdateEvent.CARADDED);
        } else throw new ControllerException("No more room for cars!");
    }

    void createRandomVehicle() throws ControllerException {
        Random randomGenerator = new Random();
        int randomizerUpperLimit = 3;
        int randomNumber = randomGenerator.nextInt((randomizerUpperLimit - 1) + 1) + 1;
        chooseCarToGenerate(randomNumber);
    }

    // This method is very not Open for extensibility, since adding more Vehicles and wanting them to be able to be randomly generated
    // would need changes in this randomizing function.
    // A better way to implement this would be to create another factory that takes a parameter of a list of factories and chooses between them.
    // I deduced that I did not have time for this implementation this time...
    private void chooseCarToGenerate(int randomNumber) throws ControllerException {
         switch (randomNumber) {
            case 1:
                addNewVehicle(new VolvoFactory());
                break;
            case 2:
                addNewVehicle(new SaabFactory());
                break;
            case 3:
                addNewVehicle(new ScaniaFactory());
                break;
        }
    }

    public void addObserver(VehicleObserver observer) {
        observers.add(observer);
    }

    public void notifyAllObservers(UpdateEvent event) {
        for (VehicleObserver o : observers) {
            o.actOnModelChange(event);
        }
    }

    public void tick(){
        boolean hasMoved = false;
        for (Vehicle v : cars){
            testCarInRange(v);
            v.move();
            if (v.getCurrentSpeed() > 0) hasMoved = true;
        }
        if (hasMoved) notifyAllObservers(UpdateEvent.MOTION);
    } // This method represents the call from the timer to the model to update.

    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Vehicle car : cars) {
            car.gas(gas);
        }
    }

    void brake(int amount) {
        double brake = ((double) amount) / 50;
        for (Vehicle car : cars) {
            car.brake(brake);
        }
    }

    void stop() {
        for (Vehicle car : cars) {
            car.stopEngine();
        }
    }

    void start() {
        for (Vehicle car : cars) {
            car.startEngine();
        }
    }

    void turboOn() {
        for (Vehicle vehicle : cars) {
            if (Objects.equals(vehicle.getModelName(), "Saab95"))
                ((Saab95)vehicle).setTurboOn();
        }
    }

    void turboOff() {
        for (Vehicle vehicle : cars) {
            if (Objects.equals(vehicle.getModelName(), "Saab95"))
                ((Saab95)vehicle).setTurboOff();
        }
    }

    void liftBed() {
        for (Vehicle vehicle : cars) {
            if (Objects.equals(vehicle.getModelName(), "Scania")) {
                ((Scania)vehicle).raiseTruckBed();
            }
        }
    }
    void lowerBed() {
        for (Vehicle vehicle : cars) {
            if (Objects.equals(vehicle.getModelName(), "Scania")) {
                ((Scania)vehicle).lowerTruckBed();
            }
        }
    }

    void testCarInRange(Vehicle car) {
        if (car.getDirection() == 180 && car.getPosition().getX() <= 0) {
            car.turnRight();
            car.turnRight();
            car.stopEngine();
        }
        else if (car.getDirection() == 0 && car.getPosition().getX() >= 700) {
            car.turnRight();
            car.turnRight();
            car.stopEngine();
        }
    }
}

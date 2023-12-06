package graphics;

import lab1.Saab95;
import lab1.Vehicle;
import lab2.Factories.SaabFactory;
import lab2.Factories.ScaniaFactory;
import lab2.Factories.IVehicleFactory;
import lab2.Factories.VolvoFactory;
import lab2.Scania;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Random;

// Should handle all functions that affect the model. A middle-man so to say.
public class CarModelAdapter {

    LinkedList<Vehicle> cars = new LinkedList<>();
    LinkedList<VehicleObserver> observers = new LinkedList<>();

    IVehicleFactory factory;

    public CarModelAdapter() {
        addNewVehicle(new VolvoFactory());
        addNewVehicle(new SaabFactory());
        addNewVehicle(new ScaniaFactory());
    }

    private void addNewVehicle(IVehicleFactory factory){
        cars.add(factory.createVehicle());
    }

    private void createRandomVehicle() {
        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt((3 - 1) + 1) + 1;
        chooseCarToGenerate(randomNumber);
    }

    // This method is very not Open for extensibility, since adding more Vehicles and wanting them to be able to be randomly generated
    // would need changes in this randomizing function.
    // A better way to implement this would be to create another
    private void chooseCarToGenerate(int randomNumber) {
       cars.add(factory.createVehicle());
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
            if (Objects.equals(vehicle.getModelName(), "Scania"))
                ((Scania)vehicle).raiseTruckBed();
        }
    }
    void lowerBed() {
        for (Vehicle vehicle : cars) {
            if (Objects.equals(vehicle.getModelName(), "Scania"))
                ((Scania)vehicle).lowerTruckBed();
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

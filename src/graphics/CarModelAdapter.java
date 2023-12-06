package graphics;

import lab1.Saab95;
import lab1.Vehicle;
import lab1.Volvo240;
import lab2.Factories.SaabFactory;
import lab2.Factories.ScaniaFactory;
import lab2.Factories.VehicleFactory;
import lab2.Factories.VolvoFactory;
import lab2.Scania;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Random;

// TODO: Should handle all functions that affect the model. A middle-man so to say.
public class CarModelAdapter {

    LinkedList<Vehicle> cars = new LinkedList<>();

    VehicleFactory factory;

    public CarModelAdapter() {
        addNewVolvo240();
        addNewSaab95();
        addNewScania();
    }

    private void addNewScania() {
        factory = new ScaniaFactory();
        cars.add(factory.createVehicle());
    }

    private void addNewSaab95() {
        factory = new SaabFactory();
        cars.add(factory.createVehicle());
    }

    private void addNewVolvo240() {
        factory = new VolvoFactory();
        cars.add(factory.createVehicle());
    }

    private void createRandomVehicle() {
        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt((3 - 1) + 1) + 1;
        chooseCarToGenerate(randomNumber);
    }

    // This method is very not Open for extensibility, since adding more Vehicles and wanting them to be able to be randomly generated
    // would need changes in this randomizing function.
    // However, I do not know how to implement this in any other way, since getting a list of all Subclasses to Vehicle seems bad.
    private void chooseCarToGenerate(int randomNumber) {
        switch (randomNumber) {
            case 1:
                addNewVolvo240();
                break;
            case 2:
                addNewSaab95();
                break;
            case 3:
                addNewScania();
                break;
        }
    }


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

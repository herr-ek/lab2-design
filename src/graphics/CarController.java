package graphics;

import lab1.*;
import lab2.*;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 16;
    // The timer is started with an listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    LinkedList<Vehicle> cars = new LinkedList<>();

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.cars.add(new Volvo240());
        cc.cars.add(new Saab95());
        cc.cars.add(new Scania());


        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Vehicle car : cars) {
                testCarInRange(car);
                car.move();
                int x = (int) Math.round(car.getPosition().getX());
                int y = (int) Math.round(car.getPosition().getY());
                frame.drawPanel.moveit(x, y, car);
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
            }
        }
    }

    // Calls the gas method for each car once
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
            if (vehicle.getModelName() == "Saab95")
                ((Saab95)vehicle).setTurboOn();
        }
    }

    void turboOff() {
        for (Vehicle vehicle : cars) {
            if (vehicle.getModelName() == "Saab95")
                ((Saab95)vehicle).setTurboOff();
        }
    }

    void liftBed(int amount) {
        for (Vehicle vehicle : cars) {
            if (vehicle.getModelName() == "Scania")
                ((Scania)vehicle).raiseTruckBed(amount);
        }
    }
    void lowerBed(int amount) {
        for (Vehicle vehicle : cars) {
            if (vehicle.getModelName() == "Scania")
                ((Scania)vehicle).lowerTruckBed(amount);
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

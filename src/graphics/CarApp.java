package graphics;

import lab1.*;
import lab2.*;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Objects;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarApp {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 16;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private final Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    LinkedList<Vehicle> cars = new LinkedList<>();

    CarModelAdapter adapter = new CarModelAdapter();

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarApp cc = new CarApp();

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc.adapter);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Vehicle car : cars) {
                adapter.testCarInRange(car);
                car.move();
                int x = (int) Math.round(car.getPosition().getX());
                int y = (int) Math.round(car.getPosition().getY());
                frame.drawPanel.moveit(x, y, car);
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
            }
        }
    }


}

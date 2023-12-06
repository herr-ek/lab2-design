package graphics;

import lab1.*;



import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
* This class represents the executable Application of the program.
* Its responsibilities are to initialize all the parts of the program and listen to the Controller and respond
* in an appropriate manner by modifying the model state and the updating the view.
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
    CarController controller;
    CarModelFacade adapter = new CarModelFacade();

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarApp carApp = new CarApp();

        // Start a new view and send a reference of self
        carApp.frame = new CarView("CarSim 1.0", carApp.adapter);

        // Start the timer
        carApp.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            adapter.tick();
        }
    }

}

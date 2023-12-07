package graphics;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
* This class represents the executable Application of the program.
* Its responsibilities are to initialize all the parts of the program and to contain the main method that continuously runs the program.
 */

public class CarApp {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 16;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private final Timer timer = new Timer(delay, new TimerListener());

    CarView frame;
    CarController controller;
    CarModelFacade adapter = new CarModelFacade(10);


    public static void main(String[] args) throws ControllerException {
        // Instance of this class
        CarApp carApp = new CarApp();

        // Start a new view and send a reference of self
        carApp.frame = new CarView("CarSim 1.0", carApp.adapter);
        carApp.adapter.fillWithCars();
        carApp.controller = new CarController(carApp.frame, carApp.adapter);

        // Start the timer
        carApp.timer.start();
    }

    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            adapter.tick();
        }
    }
}

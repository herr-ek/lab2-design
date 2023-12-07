package graphics;

import vehicles.base.Vehicle;

import javax.swing.*;
import java.awt.*;

/**
 * This class represents the full view of the MVC pattern of your car simulator.
 * It initializes with being center on the screen and attaching it's controller in it's state.
 * It communicates with the Controller by calling methods of it when an action fires of in
 * each of it's components.
 * TODO: Write more actionListeners and wire the rest of the buttons
 **/

public class CarView extends JFrame implements VehicleObserver {
    private static final int X = 800;
    private static final int Y = 800;

    // The model member
    CarModelFacade facade;

    DrawPanel drawPanel;

    // Constructor
    public CarView(String framename, CarModelFacade adapter){
        this.facade = adapter;
        this.drawPanel = new DrawPanel(X, Y-240);
        facade.addObserver(this);
        initComponents(framename);
    }

    @Override
    public void actOnModelChange(UpdateEvent event) {
        switch (event){
            case MOTION -> {
                for (Vehicle v : facade.cars) {
                    int x = (int) Math.round(v.getPosition().getX());
                    int y = (int) Math.round(v.getPosition().getY());
                    drawPanel.moveit(x,y,v);
                }
            }
            case CARADDED -> drawPanel.addMovableToPanel(facade.cars.getLast());
            case CARREMOVED -> drawPanel.removeMovableFromPanel(facade.cars.getLast());
        }
        drawPanel.repaint();
    }

    // Sets everything in place and fits everything
    private void initComponents(String title) {

        this.setTitle(title);
        this.setPreferredSize(new Dimension(X,Y));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        this.add(drawPanel);
    }

    void packView() {
        this.pack();
    }        // Make the frame pack all it's components by respecting the sizes if possible.
    void renderOnScreen() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Center the frame
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        // Make the frame visible
        this.setVisible(true);
        // Make sure the frame exits when "x" is pressed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }        // Get the computer screen resolution



}
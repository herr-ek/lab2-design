package graphics;

import lab1.Vehicle;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observer;

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


    // The controller member
    CarModelAdapter adapter;

    ButtonController buttC = new ButtonController();
    DrawPanel drawPanel;


    JPanel gasPanel = new JPanel();
    JSpinner gasSpinner = new JSpinner();
    int gasAmount = 0;
    JLabel gasLabel = new JLabel("Amount of gas");

    // Constructor
    public CarView(String framename, CarModelAdapter adapter){
        this.adapter = adapter;
        this.drawPanel = new DrawPanel(X, Y-240);
        initComponents(framename);
    }

    @Override
    public void actOnModelChange(UpdateEvent event) {
        switch (event){
            case MOTION -> {
                for (Vehicle v : adapter.cars) {
                    int x = (int) Math.round(v.getPosition().getX());
                    int y = (int) Math.round(v.getPosition().getY());
                    drawPanel.moveit(x,y,v);
                }
                break;
            }
            case NUMOFCARSCHANGE -> {

            }
        }
        drawPanel.repaint();
    }

    // Sets everything in place and fits everything
    // TODO: Take a good look and make sure you understand how these methods and components work
    private void initComponents(String title) {

        this.setTitle(title);
        this.setPreferredSize(new Dimension(X,Y));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        this.add(drawPanel);



        SpinnerModel spinnerModel =
                new SpinnerNumberModel(0, //initial value
                        0, //min
                        100, //max
                        1);//step
        gasSpinner = new JSpinner(spinnerModel);
        gasSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                gasAmount = (int) ((JSpinner)e.getSource()).getValue();
            }
        });

        gasPanel.setLayout(new BorderLayout());
        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END);

        this.add(gasPanel);

        this.add(buttC.controlPanel);

        this.add(buttC.startButton);

        this.add(buttC.stopButton);


        // This actionListener is for the gas button only
        // TODO: Create more for each component as necessary
        buttC.gasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adapter.gas(gasAmount);
            }
        });

        buttC.brakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adapter.brake(gasAmount);
            }
        });

        buttC.stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adapter.stop();
            }
        });
        buttC.startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adapter.start();
            }
        });

        buttC.turboOnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adapter.turboOn();
            }
        });
        buttC.turboOffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adapter.turboOff();
            }
        });
        buttC.liftBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adapter.liftBed();
            }
        });
        buttC.lowerBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adapter.lowerBed();
            }
        });

        // Make the frame pack all it's components by respecting the sizes if possible.
        this.pack();

        // Get the computer screen resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Center the frame
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        // Make the frame visible
        this.setVisible(true);
        // Make sure the frame exits when "x" is pressed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
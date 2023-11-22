package graphics;

import lab1.Vehicle;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represent the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    // Just a single image, TODO: Generalize
    HashMap<Vehicle, BufferedImage> images = new HashMap<>();
    //BufferedImage volvoImage;
    // To keep track of a single cars position
    //Point volvoPoint = new Point();
    HashMap<Vehicle, Point> points;


    // TODO: Make this general for all cars
    void moveit(int x, int y, Vehicle v){
        Point newPoint = new Point(x,y);
        points.replace(v, newPoint);
    }

    HashMap<Vehicle, Point> createPoints(Collection<Vehicle> v){
        HashMap<Vehicle, Point> hash = new HashMap<>();
        int y = 0;
        for (Vehicle vehicle : v){
            hash.put(vehicle, new Point(0, y));
            vehicle.getPosition().setY(y);
            y += 100;
        }
        return hash;
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y, Collection<Vehicle> vehicles) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        this.points = createPoints(vehicles);

        // Print an error message in case file is not found with a try/catch block
        try {
            // You can remove the "graphics.pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            // volvoImage = ImageIO.read(new File("Volvo240.jpg"));

            // Rememember to rightclick src New -> Package -> name: graphics.pics -> MOVE *.jpg to graphics.pics.
            // if you are starting in IntelliJ.
            volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }


    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Vehicle vehicle : images.keySet()) {
            g.drawImage(images.get(vehicle), points.get(vehicle).x, points.get(vehicle).y, null); // see javadoc for more info on the parameters
        }
    }
}

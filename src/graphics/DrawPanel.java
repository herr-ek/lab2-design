package graphics;

import lab1.Movable;

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
    HashMap<Movable, BufferedImage> images = new HashMap<>();
    HashMap<Movable, Point> points;

    // TODO: Make this general for all cars
    void moveit(int x, int y, Movable v){
        Point newPoint = new Point(x,y);
        points.replace(v, newPoint);
    }

    HashMap<Movable, Point> createPoints(Collection<Movable> v){
        HashMap<Movable, Point> hash = new HashMap<>();
        int y = 0;
        for (Movable Movable : v){
            hash.put(Movable, new Point(0, y));
            Movable.getPosition().setY(y);
            y += 100;
        }
        return hash;
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y, Collection<Movable> Movables) {
        createAndColorBackground(x, y);
        this.points = createPoints(Movables);

        // Print an error message in case file is not found with a try/catch block
        try {

            for (Movable Movable : Movables)  {
                System.out.println(this.getClass().getSimpleName());
                images.put(Movable, ImageIO.read(DrawPanel.class.getResourceAsStream("pics/"+this.getClass().getSimpleName()+".jpg")));
            }

        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }

    private void createAndColorBackground(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
    }


    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Movable Movable : images.keySet()) {
            g.drawImage(images.get(Movable), points.get(Movable).x, points.get(Movable).y, null); // see javadoc for more info on the parameters
        }
    }
}

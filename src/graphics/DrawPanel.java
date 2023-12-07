package graphics;

import vehicles.base.Movable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represent the animated part of the view with the car images.

public class DrawPanel extends JPanel{
    HashMap<Movable, BufferedImage> images = new HashMap<>();
    HashMap<Movable, Point> points = new HashMap<>();
    int yOffset = 0;

    void moveit(int x, int y, Movable v){
        Point newPoint = new Point(x,y);
        points.replace(v, newPoint);
    }

    void createAndAddPoint(Movable m){
        points.put(m, new Point(0, yOffset));
        m.getPosition().setY(yOffset);
        yOffset += 60;
    }

    public DrawPanel(int x, int y) {
        createAndColorBackground(x, y);
    }

    public void addMovableToPanel(Movable movable) {
        createAndAddPoint(movable);
        // Print an error message in case file is not found with a try/catch block
        try {
            images.put(movable, ImageIO.read(DrawPanel.class.getResourceAsStream("pics/"+movable.getClass().getSimpleName()+".jpg")));
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    public void removeMovableFromPanel(Movable movable) {
        images.remove(movable);
        points.remove(movable);
        yOffset -= 60;
    }

    private void createAndColorBackground(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Movable Movable : images.keySet()) {
            g.drawImage(images.get(Movable), points.get(Movable).x, points.get(Movable).y, null); // see javadoc for more info on the parameters
        }
    }
}

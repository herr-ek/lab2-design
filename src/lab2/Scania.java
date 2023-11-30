package lab2;

import java.awt.*;

public class Scania extends TruckBedTruck implements Loadable {
    
    public Scania() {
        super(18000 , 70);
        nrDoors = 2;
        enginePower = 100;
        color = Color.darkGray;
        modelName = "Scania";
        weight = 14000;
    }
}

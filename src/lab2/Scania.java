package lab2;

import lab2.TruckBeds.ScaniaTruckBed;

import java.awt.*;

public class Scania extends Truck {

    public Scania() {
        truckbed = new ScaniaTruckBed(18000);
        nrDoors = 2;
        enginePower = 420;
        color = Color.darkGray;
        modelName = "Scania P124";
        weight = 14000;
    }
}

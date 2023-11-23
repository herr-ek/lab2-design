package lab1;

import java.awt.*;

public class Volvo240 extends Car {

    public final double trimFactor = 1.25;

    public Volvo240(){
        nrDoors = 4;
        color = Color.black;
        enginePower = 100;
        modelName = "Volvo240";
        weight = 1400;
        stopEngine();
    }

    @Override
    protected double speedFactor(){
        return enginePower * trimFactor * 0.01;
    }



}

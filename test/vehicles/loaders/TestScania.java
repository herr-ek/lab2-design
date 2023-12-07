package vehicles.loaders;


import org.junit.Before;
import org.junit.Test;
import vehicles.base.*;

import static org.junit.Assert.assertEquals;

public class TestScania {

    public Scania scania;

    @Before
    public void init() {
        scania = new Scania();
    }

    @Test
    public void testRaiseTruckBedWithSpeed() {
        scania.startEngine();
        scania.raiseTruckBed();
        assertEquals(0, scania.truckbed.getCurrentAngle());
    }

    @Test
    public void testMoveWithTruckBedNotSecured() {
        Position initial = scania.getPosition();
        scania.raiseTruckBed();
        scania.startEngine();
        scania.move();
        assertEquals(initial, scania.getPosition());
    }
    @Test
    public void testRaiseTruckBed() {
        scania.raiseTruckBed();
        assertEquals(10, scania.truckbed.getCurrentAngle());
    }

    @Test
    public void testLowerTruckBed() {
        scania.raiseTruckBed();
        scania.raiseTruckBed();
        scania.lowerTruckBed();
        assertEquals(10, scania.truckbed.getCurrentAngle());
    }

    @Test
    public void testRaiseHigh() {
        for (int i=0; i<10; i++) {
            scania.raiseTruckBed();
        }
        assertEquals(70, scania.truckbed.getCurrentAngle());
    }

    @Test
    public void testLowerLow() {
        for (int i=0; i<11; i++)
            scania.lowerTruckBed();
        assertEquals(0, scania.truckbed.getCurrentAngle());
    }
}

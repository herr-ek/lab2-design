package vehicles.base;

import org.junit.*;

import java.awt.*;

import static org.junit.Assert.*;

public class TestVehicle {

    private Saab95 saab;
    private Volvo240 volvo;


    @Before
    public void init() {
        saab = new Saab95();
        volvo = new Volvo240();
    }

    @Test
    public void testSetColor() {
        Color volvoCol = volvo.getColor();
        Color saabCol = saab.getColor();

        volvo.setColor(Color.green);
        saab.setColor(Color.pink);

        assertTrue(!(volvoCol.equals(volvo.getColor())) && !(saabCol.equals((saab.getColor()))));
    }

    @Test
    public void testStartEngine(){
        volvo.startEngine();
        saab.startEngine();
        assertTrue((volvo.getCurrentSpeed() > 0) && (saab.getCurrentSpeed() > 0));
    }

    @Test
    public void testStopEngine() {
        volvo.startEngine();
        saab.startEngine();

        volvo.stopEngine();
        saab.stopEngine();

        assertTrue((volvo.getCurrentSpeed() == 0) && (saab.getCurrentSpeed() == 0));
    }

    @Test
    public void testNrOfDoors() {
        int volvoDoors = volvo.getNrDoors();
        int saabDoors = saab.getNrDoors();

        assertTrue((volvoDoors == 4) && (saabDoors == 2));
    }

    @Test
    public void testEnginePower() {
        double volvoPower = volvo.getEnginePower();
        double saabPower = saab.getEnginePower();

        assertTrue((volvoPower == 100) && (saabPower == 125));
    }

    @Test
    public void testTurnLeft() {
        volvo.turnLeft();
        saab.turnLeft();

        assertTrue((volvo.getDirection() == 90) && (saab.getDirection() == 90));
    }

    @Test
    public void testTurnRight() {
        volvo.turnRight();
        saab.turnRight();

        assertTrue((volvo.getDirection() == 270) && (saab.getDirection() == 270));
    }

    @Test
    public void testTurnFullRotationLeft(){
        volvo.turnLeft();
        volvo.turnLeft();
        volvo.turnLeft();
        volvo.turnLeft();

        assertEquals(0, volvo.getDirection(), 0);
    }
    @Test
    public void testTurnFullRotationRight(){
        volvo.turnRight();
        volvo.turnRight();
        volvo.turnRight();
        volvo.turnRight();

        assertEquals(0, volvo.getDirection(), 0);
    }
}

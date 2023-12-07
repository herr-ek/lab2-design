package vehicles.base;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestMove {
    private Volvo240 volvo;
    private double volvoSpeed;
    private double expectedX;
    private double expectedY;
    @Before
    public void init() {
        volvo = new Volvo240();
        volvo.startEngine();
        expectedX = 0;
        expectedY = 0;
        volvoSpeed = volvo.getCurrentSpeed();
    }

    @Test
    public void testMoveDirection0(){
        expectedX += volvoSpeed;
        volvo.move();
        Position newPosition = volvo.getPosition();

        assertTrue((newPosition.getX() == expectedX) && (newPosition.getY() == expectedY));
    }

    @Test
    public void testMoveDirection90(){
        volvo.turnLeft();
        expectedY += volvoSpeed;
        volvo.move();
        Position newPosition = volvo.getPosition();

        assertTrue((newPosition.getX() == expectedX) && (newPosition.getY() == expectedY));
    }

    @Test
    public void testMoveDirection180(){
        volvo.turnLeft();
        volvo.turnLeft();
        expectedX -= volvoSpeed;
        volvo.move();
        Position newPosition = volvo.getPosition();

        assertTrue((newPosition.getX() == expectedX) && (newPosition.getY() == expectedY));
    }

    @Test
    public void testMoveDirection270(){
        volvo.turnRight();
        expectedY -= volvoSpeed;
        volvo.move();
        Position newPosition = volvo.getPosition();

        assertTrue((newPosition.getX() == expectedX) && (newPosition.getY() == expectedY));
    }

}

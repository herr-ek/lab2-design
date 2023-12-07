package vehicles.base;

import org.junit.*;

import static org.junit.Assert.*;

public class TestVolvo {

    private Volvo240 volvo;

    @Before
    public void init() {
        volvo = new Volvo240();
    }


    @Test
    public void testVolvoSpeedFactor(){
        double Factor = volvo.speedFactor();
        assertEquals(1.25, Factor, 0);
    }

    @Test
    public void testVolvoIncrementSpeed(){
        volvo.incrementSpeed(1.0);
        assertEquals(1.25, volvo.getCurrentSpeed(), 0);
    }

    @Test
    public void testVolvoMaxSpeed(){
        volvo.incrementSpeed(10000);
        assertEquals(volvo.getEnginePower(), volvo.getCurrentSpeed(), 0);
    }

    @Test
    public void testVolvoDecrementSpeed(){
        volvo.incrementSpeed(1.0);
        volvo.decrementSpeed(.5);
        assertEquals(.625, volvo.getCurrentSpeed(), 0);
    }

    @Test
    public void testGasVolvo() {
        volvo.gas(1);
        assertEquals(1.25, volvo.getCurrentSpeed(), 0);
    }

    @Test
    public void testBrakeVolvo() {
        volvo.gas(1);
        volvo.brake(0.5);
        assertEquals(0.625, volvo.getCurrentSpeed(), 0);
    }

    @Test
    public void testVolvoCrazyGas(){
        for (int i = 0; i < 10000; i++) {
            volvo.gas(1);
        }
        assertEquals(volvo.getEnginePower(), volvo.getCurrentSpeed(), 0);
    }

    @Test
    public void testVolvoCrazyBrake(){
        volvo.gas(1);
        volvo.gas(1);
        volvo.brake(1);
        assertEquals(1.25, volvo.getCurrentSpeed(), 0);
    }

}



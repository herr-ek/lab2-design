package vehicles.base;

import org.junit.*;

import static org.junit.Assert.*;

public class TestSaab {

    private Saab95 saab;

    @Before
    public void init() {
        saab = new Saab95();
        saab.setTurboOn();

    }

    @Test
    public void testSetSaabTurboOn() {
        saab.setTurboOff();
        boolean startValue = saab.getTurbo();
        saab.setTurboOn();
        assertTrue(startValue != saab.getTurbo());
    }


    @Test
    public void testSetSaabTurboOff() {

        boolean startValue = saab.getTurbo();
        saab.setTurboOff();

        assertTrue(startValue != saab.getTurbo());
    }

    @Test
    public void testSaabSpeedFactorTurboOn() {

        double Factor = saab.speedFactor();
        assertEquals(1.625, Factor, 0);
    }

    @Test
    public void testSaabSpeedFactorTurboOff() {
        saab.setTurboOff();
        double Factor = saab.speedFactor();
        assertEquals(1.25, Factor, 0);
    }

    @Test
    public void testSaabIncrementSpeedTurboOff(){
        saab.setTurboOff();
        saab.incrementSpeed(1);
        assertEquals(1.25, saab.getCurrentSpeed(), 0);
    }

    @Test
    public void testSaabIncrementSpeedTurboOn(){

        saab.incrementSpeed(1);
        assertEquals(1.625, saab.getCurrentSpeed(), 0);
    }

    @Test
    public void testSaabDecrementSpeedTurboOff(){
        saab.setTurboOff();
        saab.incrementSpeed(1);
        saab.decrementSpeed(0.5);
        assertEquals(0.625, saab.getCurrentSpeed(), 0);
    }

    @Test
    public void testSaabDecrementSpeedTurboOn(){

        saab.incrementSpeed(1);
        saab.decrementSpeed(0.5);
        assertEquals(0.8125, saab.getCurrentSpeed(), 0.01);
    }

    @Test
    public void testSaabGasTurboOn() {

        saab.gas(1);
        assertEquals(1.625, saab.getCurrentSpeed(), 0);
    }

    @Test
    public void testSaabGasTurboOff() {
        saab.setTurboOff();
        saab.gas(1);
        assertEquals(1.25, saab.getCurrentSpeed(), 0);
    }

    @Test
    public void testSaabBrakeTurboOn() {

        saab.gas(1);
        saab.brake(0.5);
        assertEquals(0.8125, saab.getCurrentSpeed(), 0);
    }

    @Test
    public void testSaabBrakeTurboOff() {
        saab.setTurboOff();
        saab.gas(1);
        saab.brake(0.5);
        assertEquals(0.625, saab.getCurrentSpeed(), 0);
    }

    @Test
    public void testSaabCrazySpeed(){
        for (int i = 0; i < 10000; i++) {
            saab.gas(1);
        }
        assertTrue(saab.getCurrentSpeed() <= saab.getEnginePower());
    }

    @Test
    public void testSaabCrazyBrake(){
        saab.setTurboOff();
        saab.gas(1);
        saab.gas(1);
        saab.brake(1000000);
        assertEquals(1.25, saab.getCurrentSpeed(), 0.0);

    }
    @Test
    public void testSaabHighGasTurboOn(){

        saab.gas(1);
        double cappedGas = saab.getCurrentSpeed();
        saab.gas(10);
        assertEquals(saab.getCurrentSpeed(), cappedGas * 2, 0.0);
    }

    @Test
    public void testSaabHighGasTurboOff(){
        saab.setTurboOff();
        saab.gas(1);
        double cappedGas = saab.getCurrentSpeed();
        saab.gas(10);
        assertEquals(saab.getCurrentSpeed(), cappedGas * 2, 0.0);
    }
}

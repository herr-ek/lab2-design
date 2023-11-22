package lab1;

import java.awt.*;

public abstract class Vehicle implements Movable {
    protected int nrDoors;
    protected double enginePower;
    protected double currentSpeed = 0;
    protected Color color;
    protected String modelName;
    protected Position position = new Position(0,0);
    protected int direction = 0;
    protected int weight;

    public int getNrDoors(){
        return nrDoors;
    }

    public int getWeight() {
        return weight;
    }

    public double getEnginePower(){
        return enginePower;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public void setColor(Color clr) {
        color = clr;
    }

    public Color getColor(){
        return color;
    }

    public String getModelName(){
        return modelName;
    }

    public void startEngine(){
        currentSpeed = 0.1;
    }

    public void stopEngine(){
        currentSpeed = 0;
    }

    protected double speedFactor() {
        return enginePower * 0.01;
    }

    // Math.min ensures that currentSpeed cannot reach above enginePower
    protected void incrementSpeed(double amount) {
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount,enginePower);
    }

    // Math.max ensures that currentSpeed cannot reach below 0
    protected void decrementSpeed(double amount) {
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }

    //Math.x lines ensures that speed cannot be increased by >1 or <0
    public void gas(double amount){
        double upperCheck = Math.min(amount, 1);
        double adjustedAmount = Math.max(upperCheck, 0);
        incrementSpeed(adjustedAmount);
    }

    //Math.x lines ensures that speed cannot be decreased by >1 or <0
    public void brake(double amount){
        double upperCheck = Math.min(amount, 1);
        double adjustedAmount = Math.max(upperCheck, 0);
        decrementSpeed(adjustedAmount);
    }

    @Override
    public void move() {
        switch(direction) {
            case 0: // increase x
                position.setX(position.getX() + getCurrentSpeed());
                break;
            case 90: // increase y
                position.setY(position.getY() + getCurrentSpeed());
                break;
            case 180: // decrease x
                position.setX(position.getX() - getCurrentSpeed());
                break;
            case 270: // decrease y
                position.setY(position.getY() - getCurrentSpeed());
                break;
        }
    }

    // Modulo 360 to ensure that values over 360 are not possible
    @Override
    public void turnLeft() {
        direction = (direction + 90) % 360;
    }

    @Override
    public void turnRight() {
        direction = ((direction - 90) % 360 + 360) % 360; // Java modulo negative numbers does not return expected value. This is a workaround.
    }

    @Override
    public Position getPosition() { return position; }

    @Override
    public int getDirection() { return direction; }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}

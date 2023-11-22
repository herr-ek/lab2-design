package lab1;// Class is used to store doubles x and y referring to the positions of Cars.

public class Position {
    private double x;
    private double y;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double absDistance( Position otherPosition) {
        double xDistance = Math.abs(getX() - otherPosition.getX());
        double yDistance = Math.abs(getY() - otherPosition.getY());
        return Math.sqrt(Math.pow(xDistance,2) + Math.pow(yDistance, 2));
    }
}

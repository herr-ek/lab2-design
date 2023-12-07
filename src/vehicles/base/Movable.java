package vehicles.base;

public interface Movable {
     void move();
     void turnLeft();
     void turnRight();
     Position getPosition();
     int getDirection();
}

package lab2;

import lab1.Position;

public interface Loadable {
    int getWeight();

    Position getPosition();

    void setDirection(int newDirection);
}

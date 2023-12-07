package vehicles.loaders;

import vehicles.base.Position;

public interface Loadable {
    int getWeight();

    Position getPosition();

    String getModelName();

    void setDirection(int newDirection);
}

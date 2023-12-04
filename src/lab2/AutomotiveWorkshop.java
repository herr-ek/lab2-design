package lab2;

import lab1.Car;


public class AutomotiveWorkshop<T extends Car>{
    private final LoadCarrier<T> loadCarrier;

    public AutomotiveWorkshop(int maxUnits) {
        loadCarrier = new LoadCarrier<>(maxUnits);
    }

    public void add(T vehicle) throws LoaderException {
        if (loadCarrier.sizeOfCargo() < loadCarrier.getMaxUnits())
            loadCarrier.load(vehicle);
        else
            throw new LoaderException("Workshop is full");
    }

    public T checkOut(T vehicle) throws LoaderException {
        return (loadCarrier.unload(vehicle));
    }
}


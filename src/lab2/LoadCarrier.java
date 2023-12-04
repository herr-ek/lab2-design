package lab2;

import lab1.Vehicle;

import java.util.LinkedList;

public class LoadCarrier<T> {
    private final LinkedList<T> cargo = new LinkedList<>();
    private int maxUnits;

    public LoadCarrier(int maxUnits){
        this.maxUnits = maxUnits;
    }
    //public LoadCarrier(){}

    public int getMaxUnits() {
        return maxUnits;
    }

    public void load(T vehicle){
        cargo.add((vehicle));
    }

    public T unload(T vehicle) throws LoaderException {
        if (cargo.remove(vehicle))
            return vehicle;
        else
            throw new LoaderException("Vehicle not found");
    }

    public T unloadLast() {
        return cargo.removeLast();
    }

    public LinkedList<T> getCargo(){
        return cargo;
    }

    public int sizeOfCargo(){
        return cargo.size();
    }

    // A loader helper that does the loading functions per request of the cargo-holder
}
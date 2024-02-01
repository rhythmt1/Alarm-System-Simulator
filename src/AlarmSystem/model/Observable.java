package AlarmSystem.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {
    protected List<Observer> observers;

    //Constructor creates an empty list of observers
    public Observable() {
        observers = new ArrayList<Observer>();
    }

    //Adds an observer to the list of observers
    public void addObserver(Observer o) {
        observers.add(o);
    }

    //Notifies the observers when a change occurs in the status of this Observable.
    public abstract void notifyObservers();
}

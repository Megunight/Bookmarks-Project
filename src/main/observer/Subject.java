package observer;

import model.Library;

import java.util.ArrayList;
import java.util.List;

public interface Subject {
    List<OperationsObserver> observers = new ArrayList<>();

    void addObserver(OperationsObserver o);

    void removeObserver(OperationsObserver o);

    void notifyObservers(Library library);
}

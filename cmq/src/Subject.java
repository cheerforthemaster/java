import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Subject {

    private List<Observer> observers
            = new ArrayList<Observer>();
    private String state;

    public String getState() {
        return state;
    }

    public void setStat(String state) {
        this.state = state;

        notifyAllObservers();
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void notifyAllObservers(){
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
package com.zyy.designpattern;

import java.util.Observable;
import java.util.Observer;

import org.junit.Test;
/**
 * 观察者模式
 * @author Shinelon
 *
 */
public class ObserverDemo {

    @Test
    public void testObserver() {
        ObserverDemoObserver observer01 = new ObserverDemoObserver("observer01");
        ObserverDemoObserver observer02 = new ObserverDemoObserver("observer02");

        ObserverDemoObservable observable = new ObserverDemoObservable();
        observable.addObserver(observer01);
        observable.addObserver(observer02);

        observable.setChanged();

        observable.notifyObservers("wozoule");
    }
}

class ObserverDemoObserver implements Observer {

    private String name;

    public ObserverDemoObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(name);
    }

}

class ObserverDemoObservable extends Observable {

    @Override
    public synchronized void setChanged() {
        super.setChanged();
    }

}

package com.example.user.myapplication;

import java.util.Observable;

public class ObservableObject extends Observable {

    private static ObservableObject instance = new ObservableObject();
    public static ObservableObject getInstance() {
        return instance;
    }

    public ObservableObject() {
    }

    public void updateValue() {
        synchronized (this) {
            setChanged();
            notifyObservers();
        }
    }

    public void updateValue(String text) {
        synchronized (this) {
            setChanged();
            notifyObservers(text);
        }
    }
}


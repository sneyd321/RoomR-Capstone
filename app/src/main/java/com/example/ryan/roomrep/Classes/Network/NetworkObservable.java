package com.example.ryan.roomrep.Classes.Network;

public interface NetworkObservable {

    void registerObserver(FragmentEventListener fragmentEventListener);

    void clearObserver();

    void notifyObserver(String response);
}

package com.example.ryan.roomrep.Classes.Network;

public interface NetworkObservable {

    void registerObserver(NetworkObserver networkObserver);

    void clearObserver();

    void notifyObserver(String response);
}

package com.example.ryan.roomrep.ViewModels;

import android.app.Application;

import com.example.ryan.roomrep.Classes.Database.HomeownerRepository;
import com.example.ryan.roomrep.Classes.Users.Homeowner;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class HomeownerViewModel extends AndroidViewModel {

    private HomeownerRepository repository;

    public HomeownerViewModel(@NonNull Application application) {
        super(application);
        repository = new HomeownerRepository(application);
    }


    public void insert(Homeowner homeowner) {
        repository.insert(homeowner);
    }

    public void delete(Homeowner homeowner) {
        repository.delete(homeowner);
    }

    public void update(Homeowner homeowner){
        repository.update(homeowner);
    }

    public LiveData<Homeowner> getHomeowner(String email) {
        return repository.getHomeowner(email);
    }

}

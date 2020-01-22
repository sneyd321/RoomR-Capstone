package com.example.ryan.roomrep.Classes;

import android.app.Application;

import com.example.ryan.roomrep.Classes.Database.HouseRepository;
import com.example.ryan.roomrep.Classes.House.House;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class HouseViewModel extends AndroidViewModel {


    private HouseRepository repository;
    private LiveData<List<House>> allHouses;
    private final MutableLiveData<House>  selected = new MutableLiveData<>();


    public HouseViewModel(@NonNull Application application) {
        super(application);
        repository = new HouseRepository(application);
        allHouses = repository.getAllHouses();
    }


    public void select(House house) {
        this.selected.setValue(house);
    }

    public LiveData<House> getSelected() {
        return this.selected;
    }



    public void insert(House house) {
        repository.insert(house);
    }

    public void delete(House house) {
        repository.delete(house);
    }

    public void update(House house){
        repository.update(house);
    }

    public LiveData<List<House>> getAllHouses() {
        return allHouses;
    }










}

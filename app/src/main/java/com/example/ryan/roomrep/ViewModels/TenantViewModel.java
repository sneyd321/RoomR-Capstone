package com.example.ryan.roomrep.ViewModels;

import android.app.Application;

import com.example.ryan.roomrep.Classes.Database.HouseRepository;
import com.example.ryan.roomrep.Classes.Database.TenantRepository;
import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.Users.Tenant;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class TenantViewModel extends AndroidViewModel {

    private TenantRepository repository;
    private LiveData<List<Tenant>> allTenants;
    private final MutableLiveData<Tenant> selected = new MutableLiveData<>();


    public TenantViewModel(@NonNull Application application) {
        super(application);
        repository = new TenantRepository(application);
        allTenants = repository.getAllTenants();
    }


    public void setSelected(Tenant tenant) {
        this.selected.setValue(tenant);
    }

    public LiveData<Tenant> getSelected() {
        return this.selected;
    }

    public void insert(Tenant tenant) {
        repository.insert(tenant);
    }

    public void delete(Tenant tenant) {
        repository.delete(tenant);
    }

    public void update(Tenant tenant){
        repository.update(tenant);
    }

    public LiveData<List<Tenant>> getAllTenants() {
        return allTenants;
    }

}

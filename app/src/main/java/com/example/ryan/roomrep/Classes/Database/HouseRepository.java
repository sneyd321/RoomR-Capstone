package com.example.ryan.roomrep.Classes.Database;

import android.app.Application;
import android.os.AsyncTask;

import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.House.Rent;

import java.util.List;

import androidx.lifecycle.LiveData;

public class HouseRepository {

    private HouseDao houseDao;
    private LiveData<List<House>> allHouses;

    public HouseRepository(Application application) {
        HouseDatabase database = HouseDatabase.getInstance(application);
        houseDao = database.houseDao();
        allHouses = houseDao.getAllHouses();
    }


    public void insert(House house) {
        new InsertHouseAsyncTask(houseDao).execute(house);
    }

    public void update(House house) {
        new UpdateHouseAsyncTask(houseDao).execute(house);
    }

    public void delete(House house) {
        new DeleteHouseAsyncTask(houseDao).execute(house);
    }

    public LiveData<List<House>> getAllHouses() {
        return this.allHouses;
    }


    private static class InsertHouseAsyncTask extends AsyncTask<House, Void, Void> {
        private HouseDao houseDao;

        private InsertHouseAsyncTask(HouseDao houseDao){
            this.houseDao = houseDao;
        }

        @Override
        protected Void doInBackground(House... houses) {
            houseDao.insert(houses[0]);
            return null;
        }
    }

    private static class UpdateHouseAsyncTask extends AsyncTask<House, Void, Void> {
        private HouseDao houseDao;

        private UpdateHouseAsyncTask(HouseDao houseDao){
            this.houseDao = houseDao;
        }

        @Override
        protected Void doInBackground(House... houses) {
            houseDao.update(houses[0]);
            return null;
        }
    }
    private static class DeleteHouseAsyncTask extends AsyncTask<House, Void, Void> {
        private HouseDao houseDao;

        private DeleteHouseAsyncTask(HouseDao houseDao) {
            this.houseDao = houseDao;
        }

        @Override
        protected Void doInBackground(House... houses) {
            houseDao.delete(houses[0]);
            return null;
        }
    }



}

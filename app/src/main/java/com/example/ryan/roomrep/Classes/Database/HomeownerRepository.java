package com.example.ryan.roomrep.Classes.Database;

import android.app.Application;
import android.os.AsyncTask;

import com.example.ryan.roomrep.Classes.Users.Homeowner;

import androidx.lifecycle.LiveData;

public class HomeownerRepository {

    private HomeownerDao homeownerDao;


    public HomeownerRepository(Application application) {
        RoomRDatabase database = RoomRDatabase.getInstance(application);
        homeownerDao = database.homeownerDao();
    }


    public void insert(Homeowner homeowner) {
        new HomeownerRepository.InsertHomeownerAsyncTask(homeownerDao).execute(homeowner);
    }

    public void update(Homeowner homeowner) {
        new HomeownerRepository.UpdateHomeownerAsyncTask(homeownerDao).execute(homeowner);
    }

    public void delete(Homeowner homeowner) {
        new HomeownerRepository.DeleteHomeownerAsyncTask(homeownerDao).execute(homeowner);
    }

    public LiveData<Homeowner> getHomeowner(String email) {
        return homeownerDao.getHomeownerByEmail(email);
    }


    private static class InsertHomeownerAsyncTask extends AsyncTask<Homeowner, Void, Void> {
        private HomeownerDao homeownerDao;

        private InsertHomeownerAsyncTask(HomeownerDao homeownerDao){
            this.homeownerDao = homeownerDao;
        }

        @Override
        protected Void doInBackground(Homeowner... homeowners) {
            homeownerDao.insert(homeowners[0]);
            return null;
        }
    }

    private static class UpdateHomeownerAsyncTask extends AsyncTask<Homeowner, Void, Void> {
        private HomeownerDao homeownerDao;

        private UpdateHomeownerAsyncTask(HomeownerDao houseDao){
            this.homeownerDao = houseDao;
        }

        @Override
        protected Void doInBackground(Homeowner... homeowners) {
            homeownerDao.update(homeowners[0]);
            return null;
        }
    }
    private static class DeleteHomeownerAsyncTask extends AsyncTask<Homeowner, Void, Void> {
        private HomeownerDao homeownerDao;

        private DeleteHomeownerAsyncTask(HomeownerDao houseDao) {
            this.homeownerDao = houseDao;
        }

        @Override
        protected Void doInBackground(Homeowner... homeowners) {
            homeownerDao.delete(homeowners[0]);
            return null;
        }
    }

}

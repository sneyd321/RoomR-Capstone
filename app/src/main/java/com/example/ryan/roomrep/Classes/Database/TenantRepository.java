package com.example.ryan.roomrep.Classes.Database;

import android.app.Application;
import android.os.AsyncTask;

import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.Users.Tenant;

import java.util.List;

import androidx.lifecycle.LiveData;

public class TenantRepository {

    private TenantDao tenantDao;
    private LiveData<List<Tenant>> allTenants;

    public TenantRepository(Application application) {
        RoomRDatabase database = RoomRDatabase.getInstance(application);
        tenantDao = database.tenantDao();
        allTenants = tenantDao.getTenants();
    }


    public void insert(Tenant tenant) {
        new TenantRepository.InsertTenantAsyncTask(tenantDao).execute(tenant);
    }

    public void update(Tenant tenant) {
        new TenantRepository.UpdateTenantAsyncTask(tenantDao).execute(tenant);
    }

    public void delete(Tenant tenant) {
        new TenantRepository.DeleteTenantAsyncTask(tenantDao).execute(tenant);
    }

    public LiveData<List<Tenant>> getAllTenants() {
        return this.allTenants;
    }


    private static class InsertTenantAsyncTask extends AsyncTask<Tenant, Void, Void> {
        private TenantDao tenantDao;

        private InsertTenantAsyncTask(TenantDao tenantDao){
            this.tenantDao = tenantDao;
        }

        @Override
        protected Void doInBackground(Tenant... tenants) {
            tenantDao.insert(tenants[0]);
            return null;
        }
    }

    private static class UpdateTenantAsyncTask extends AsyncTask<Tenant, Void, Void> {
        private TenantDao tenantDao;

        private UpdateTenantAsyncTask(TenantDao tenantDao){
            this.tenantDao = tenantDao;
        }

        @Override
        protected Void doInBackground(Tenant... tenants) {
            tenantDao.update(tenants[0]);
            return null;
        }
    }
    private static class DeleteTenantAsyncTask extends AsyncTask<Tenant, Void, Void> {
        private TenantDao tenantDao;

        private DeleteTenantAsyncTask(TenantDao tenantDao) {
            this.tenantDao = tenantDao;
        }

        @Override
        protected Void doInBackground(Tenant... tenants) {
            tenantDao.delete(tenants[0]);
            return null;
        }
    }
}

package com.example.lab2;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PhoneRepository {
    private PhoneDao mPhoneDao;
    private LiveData<List<Phone>> mAllPhones;

    public PhoneRepository(Application application){
        PhoneRoomDatabase phoneRoomDatabase =
                PhoneRoomDatabase.getDatabase(application);

        mPhoneDao = phoneRoomDatabase.phoneDao();
        mAllPhones = mPhoneDao.getAllElements();
    }

   LiveData<List<Phone>> getAllPhones(){
        return mPhoneDao.getAllElements();
   }

    void deleteAll(){
        PhoneRoomDatabase.databaseWriteExecutor.execute(()->{
        mPhoneDao.deleteAll();
        });
    }

    void update(Phone phone){
        PhoneRoomDatabase.databaseWriteExecutor.execute(()->{
            mPhoneDao.update(phone);
        });
    }

    void insert(Phone phone){
        PhoneRoomDatabase.databaseWriteExecutor.execute(()->{
            mPhoneDao.insert(phone);
        });
    }

    void delete(Phone phone){
        PhoneRoomDatabase.databaseWriteExecutor.execute(()->{
            mPhoneDao.delete(phone);
        });
    }

}

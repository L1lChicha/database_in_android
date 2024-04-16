package com.example.lab2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PhoneViewModel extends AndroidViewModel {
    private final PhoneRepository mRepository;
    private final LiveData<List<Phone>> mAllPhones;

    public PhoneViewModel(@NonNull Application application){
        super(application);
        mRepository = new PhoneRepository(application);
        mAllPhones = mRepository.getAllPhones();

        PhoneRoomDatabase database = PhoneRoomDatabase.getDatabase(this.getApplication());
    }

    public void deleteAll(){
        mRepository.deleteAll();
    }


   public void update(Phone phone){
        mRepository.update(phone);
   }

   public void insert(Phone phone){
        mRepository.insert(phone);
   }
   public void delete(Phone phone){
        mRepository.delete(phone);
   }

    public LiveData<List<Phone>> getAllPhones(){
        return mAllPhones;
    }



}

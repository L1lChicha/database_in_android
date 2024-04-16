package com.example.lab2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface PhoneDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(Phone phone);

    @Update
    void update(Phone phone);

    @Delete
    void delete(Phone phone);

    @Query("DELETE FROM phonesData")
    void deleteAll();

    @Query("SELECT * FROM phonesData ORDER BY id ASC")
    LiveData<List<Phone>> getAllElements();


    @Query("SELECT * FROM phonesData WHERE id = :id")
    Phone getById(long id);


}

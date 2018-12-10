package com.example.diego.quake.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface QuakeDao {

    @Query("SELECT * FROM quake ORDER BY time DESC")
    LiveData<List<QuakeEntry>> loadAllQuakes();

    @Query("SELECT * FROM quake WHERE magnitude>=5 ORDER BY time DESC")
    LiveData<List<QuakeEntry>> loadMagnitudeFiveQuakes();

    @Insert
    void insert(QuakeEntry quakeEntry);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertQuakes(List<QuakeEntry> quakes);

    @Query("SELECT * FROM quake ORDER BY time DESC")
    List<QuakeEntry> loadAllEarthquakesBlocking();
}

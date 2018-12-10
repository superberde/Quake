package com.example.diego.quake.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.util.Log;

@Database(entities = {QuakeEntry.class}, version = 1, exportSchema = false)
@TypeConverters(QuakeConverter.class)
public abstract class QuakeDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "quakereport";
    private static QuakeDatabase sInstance;

    private static final String TAG = QuakeDatabase.class.getSimpleName();

    public static QuakeDatabase getInstance(Context context) {
        if (sInstance == null) {
            Log.d(TAG, "getInstance: creating new database instance");
            sInstance = Room.databaseBuilder(context.getApplicationContext(), QuakeDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        Log.d(TAG, "getInstance: getting the database instance");
        return sInstance;
    }

    public abstract QuakeDao quakeDao();
}

package com.example.diego.quake.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

@Entity(tableName = "quake")
public class QuakeEntry {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    private String mId;

    @ColumnInfo(name = "time")
    private long mTime;

    @ColumnInfo(name = "place")
    private String mPlace;

    @ColumnInfo(name = "magnitude")
    private double mMagnitude;

    @ColumnInfo(name = "link")
    private String mLink;

    @ColumnInfo(name = "coordinates")
    private List<Double> mCoordinates;

    public QuakeEntry(@NonNull String id, long time, String place, double magnitude, String link, List<Double> coordinates) {
        mId = id;
        mTime = time;
        mPlace = place;
        mMagnitude = magnitude;
        mLink = link;
        mCoordinates = coordinates;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH.mm", Locale.ITALY);
        String dateString = sdf.format(mTime);
        return dateString + ": " + mMagnitude + " " + mPlace;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof QuakeEntry)
            return (((QuakeEntry)obj).getId().contentEquals(mId));
        else
            return false;
    }

    @NonNull
    public String getId() {
        return mId;
    }

    public long getTime() {
        return mTime;
    }

    public String getPlace() {
        return mPlace;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public String getLink() {
        return mLink;
    }

    public List<Double> getCoordinates() {
        return mCoordinates;
    }
}

package com.example.diego.quake.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.diego.quake.worker.QuakeWorker;

import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkStatus;


public class QuakeViewModel extends AndroidViewModel {

    private WorkManager mWorkManager;
    private LiveData<List<WorkStatus>> mWorkStatus;

    public QuakeViewModel(@NonNull Application application) {
        super(application);

        mWorkManager = WorkManager.getInstance();

        PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(QuakeWorker.class,
                15,
                TimeUnit.MINUTES)
                .build();

        mWorkManager.enqueueUniquePeriodicWork("quake_work", ExistingPeriodicWorkPolicy.KEEP, periodicWorkRequest);

    }

    public LiveData<List<WorkStatus>> getWorkStatus() {
        return mWorkStatus;
    }
}

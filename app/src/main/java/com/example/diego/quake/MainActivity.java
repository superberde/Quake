package com.example.diego.quake;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.diego.quake.adapter.QuakeAdapter;
import com.example.diego.quake.database.QuakeDatabase;
import com.example.diego.quake.database.QuakeEntry;
import com.example.diego.quake.viewmodel.QuakeViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.work.WorkManager;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String QUAKE_REQUEST = "com.example.diego.quake-request";

    private RecyclerView mRecyclerView;
    private QuakeAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.ItemAnimator mItemAnimator;
    private RecyclerView.ItemDecoration mItemDecoration;
    private List<QuakeEntry> mQuakesData;

    // BottomNavigationView
    private BottomNavigationView mBottomNavigationView;

    private QuakeViewModel mQuakeViewModel;
    private WorkManager mWorkManager;
    private QuakeDatabase quakeDatabase;

    private boolean mIsUpdated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupRecyclerView();

        // Shared Elements Transition
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Fade fade = new Fade();
            fade.excludeTarget(R.id.action_bar_container, true);
            fade.excludeTarget(android.R.id.statusBarBackground, true);
            fade.excludeTarget(android.R.id.navigationBarBackground, true);

            getWindow().setEnterTransition(fade);
            getWindow().setExitTransition(fade);
        }

        // BottomNavigationView
        setupBottomNavigationView();

        // Get the ViewModel
        if (mIsUpdated) {
            Log.d(TAG, "onCreate: already updated!!!");
        } else {
            mQuakeViewModel = ViewModelProviders.of(this).get(QuakeViewModel.class);
            mIsUpdated = true;
        }


        // Get the database
        quakeDatabase = QuakeDatabase.getInstance(this);

        // Retrieve all quakes
        retrieveAllQuakes();

    }

    private void retrieveAllQuakes() {
        LiveData<List<QuakeEntry>> quakes = quakeDatabase.quakeDao().loadAllQuakes();
        quakes.observe(this, new Observer<List<QuakeEntry>>() {
            @Override
            public void onChanged(@Nullable List<QuakeEntry> quakeEntries) {
                mRecyclerView.setAdapter(new QuakeAdapter(MainActivity.this, quakeEntries));
            }
        });
    }

    private void setupBottomNavigationView() {

        mBottomNavigationView = findViewById(R.id.navigation);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        retrieveAllQuakes();
                        return true;
                    case R.id.navigation_dashboard:
                        LiveData<List<QuakeEntry>> mag5Quakes = quakeDatabase.quakeDao().loadMagnitudeFiveQuakes();
                        mag5Quakes.observe(MainActivity.this, new Observer<List<QuakeEntry>>() {
                            @Override
                            public void onChanged(@Nullable List<QuakeEntry> quakeEntries) {
                                mRecyclerView.setAdapter(new QuakeAdapter(MainActivity.this, quakeEntries));
                            }
                        });
                        return true;
                    case R.id.navigation_notifications:
                        Toast.makeText(MainActivity.this, "NOTIF", Toast.LENGTH_SHORT).show();
                        return true;
                }
                return false;
            }
        });

    }

    private void setupRecyclerView() {
        mRecyclerView = findViewById(R.id.quake_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mQuakesData = new ArrayList<>();

        mItemAnimator = new DefaultItemAnimator();
        mRecyclerView.setItemAnimator(mItemAnimator);

        mItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(mItemDecoration);

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new QuakeAdapter(this, mQuakesData);
        mRecyclerView.setAdapter(mAdapter);
    }

}

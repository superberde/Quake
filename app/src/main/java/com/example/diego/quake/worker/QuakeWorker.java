package com.example.diego.quake.worker;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.diego.quake.MainActivity;
import com.example.diego.quake.R;
import com.example.diego.quake.data.Quake;
import com.example.diego.quake.database.QuakeDatabase;
import com.example.diego.quake.database.QuakeEntry;
import com.example.diego.quake.utils.VolleyUtils;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.work.Worker;

public class QuakeWorker extends Worker {

    private static final String TAG = QuakeWorker.class.getSimpleName();

    @NonNull
    @Override
    public Result doWork() {

        // To get quake GeoJSON data from USGS, format data and insert into a database
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_day.geojson",
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        // To get quake GeoJSON data from USGS
                        List<Quake.FeaturesBean> mFeaturesBean = getQuakeJsonData(response);

                        // Insert list of quakes into database
                        insertIntoDatabase(mFeaturesBean);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "onErrorResponse: ", error);
                    }
                });

        VolleyUtils.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest, MainActivity.QUAKE_REQUEST);

        return Result.SUCCESS;
    }

    /**
     * To get quake GeoJSON data from USGS
     *
     * @param response from the server
     * @return list of quakes
     */
    private List<Quake.FeaturesBean> getQuakeJsonData(JSONObject response) {
        Gson gson = new Gson();
        Quake quake = gson.fromJson(response.toString(), Quake.class);
        return quake.getFeatures();
    }

    /**
     * Format data from the USGS server and insert into the database
     *
     * @param mFeaturesBean of quakes
     */
    private void insertIntoDatabase(List<Quake.FeaturesBean> mFeaturesBean) {

        List<QuakeEntry> earthquakes = new ArrayList<>();

        for (Quake.FeaturesBean currentQuake : mFeaturesBean) {

            String id = currentQuake.getId();

            // Date
            long time = currentQuake.getProperties().getTime();

            // Location
            String place = currentQuake.getProperties().getPlace();

            // Magnitude
            double magnitude = currentQuake.getProperties().getMag();

            // Link
            String link = currentQuake.getProperties().getUrl();

            // Coordinates
            List<Double> coordinates = currentQuake.getGeometry().getCoordinates();

            com.example.diego.quake.database.QuakeEntry quakeEntry = new com.example.diego.quake.database.QuakeEntry(id, time, place, magnitude, link, coordinates);
            earthquakes.add(quakeEntry);

        }

        QuakeDatabase quakeDatabase = QuakeDatabase.getInstance(getApplicationContext());
        quakeDatabase.quakeDao().insertQuakes(earthquakes);

        Log.d(TAG, "insertIntoDatabase: " + earthquakes.toString());

        sendNotification(findLargestNewEarthquake(earthquakes));
    }

    private void sendNotification(QuakeEntry quakeEntry) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "channel name";
            String description = "description name";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("channel id", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getApplicationContext().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        if (quakeEntry != null) {

            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "channel id")
                    // Set required fields, including the small icon, the notification title, and text.
                    .setSmallIcon(R.drawable.ic_stat_utils)
                    .setContentTitle("Magnitude: " + quakeEntry.getMagnitude())
                    .setContentText("Details: " + quakeEntry.getLink());

            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
            notificationManagerCompat.notify(2000, builder.build());

        } else {

            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "channel id")
                    // Set required fields, including the small icon, the notification title, and text.
                    .setSmallIcon(R.drawable.ic_stat_utils)
                    .setContentTitle("Sync!")
                    .setContentText("New data downloaded");

            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
            notificationManagerCompat.notify(1005, builder.build());
        }


    }

    /**
     * To find the largest new earthquake
     *
     * @param newEarthquakes
     * @return a BIG quake
     */
    private QuakeEntry findLargestNewEarthquake(List<QuakeEntry> newEarthquakes) {

        List<QuakeEntry> earthquakes = QuakeDatabase
                .getInstance(getApplicationContext())
                .quakeDao()
                .loadAllEarthquakesBlocking();

        QuakeEntry largestNewEarthquake = null;

        for (QuakeEntry earthquake : newEarthquakes) {
            if (earthquakes.contains(earthquake)) {
            }

            if (largestNewEarthquake == null || earthquake.getMagnitude() > largestNewEarthquake.getMagnitude()) {
                largestNewEarthquake = earthquake;
            }
        }
        return largestNewEarthquake;
    }

}

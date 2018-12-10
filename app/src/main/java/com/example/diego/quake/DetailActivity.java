package com.example.diego.quake;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.view.MenuItem;

import com.example.diego.quake.databinding.ActivityDetailBinding;
import com.example.diego.quake.utils.QuakeAdapterUtils;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = DetailActivity.class.getSimpleName();

    public static final String EXTRA_MAG = "com.example.diego.quake.magnitude";
    public static final String EXTRA_COLOR = "com.example.diego.quake.color";
    public static final String EXTRA_URL = "com.example.diego.quake.url";
    public static final String EXTRA_LAT = "com.example.diego.quake.lat";
    public static final String EXTRA_LNG = "com.example.diego.quake.lng";
    public static final String EXTRA_DEPTH = "com.example.diego.quake.depth";
    public static final String EXTRA_TRANSITION_NAME = "com.example.diego.quake.transition-name";

    private ActivityDetailBinding mActivityDetailBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Data Binding
        mActivityDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        // Shared Elements Transition
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Fade fade = new Fade();
            fade.excludeTarget(R.id.action_bar_container, true);
            fade.excludeTarget(android.R.id.statusBarBackground, true);
            fade.excludeTarget(android.R.id.navigationBarBackground, true);

            getWindow().setEnterTransition(fade);
            getWindow().setExitTransition(fade);
        }


        // To allow Up navigation with the app icon in the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getIntent() != null && getIntent().hasExtra(EXTRA_MAG) && getIntent().hasExtra(EXTRA_COLOR)) {

            // Magnitude
            double mag = getIntent().getDoubleExtra(EXTRA_MAG, 0.0);
            mActivityDetailBinding.magnitudeDetail.setText(QuakeAdapterUtils.formatMagnitude(mag));

            // Color magnitude
            int colorMagnitude = getIntent().getIntExtra(EXTRA_COLOR, R.color.magnitude1);
            GradientDrawable circle = (GradientDrawable) mActivityDetailBinding.magnitudeDetail.getBackground();
            circle.setColor(colorMagnitude);

            // Url
            String url = getIntent().getStringExtra(EXTRA_URL);
            mActivityDetailBinding.urlDetail.setText(url);

            // Coordinates
            double lat = getIntent().getDoubleExtra(EXTRA_LAT, 0);
            double lng = getIntent().getDoubleExtra(EXTRA_LNG, 0);
            double depth = getIntent().getDoubleExtra(EXTRA_DEPTH, 0);
            mActivityDetailBinding.coordinatesDetail.setText("Lat: " + lat + " - Lng: " + lng + " - Depth: " + depth);

            // Share Elements Transition
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mActivityDetailBinding.magnitudeDetail.setTransitionName(getIntent().getStringExtra(EXTRA_TRANSITION_NAME));
            }

        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // To navigate up when the user presses the app icon
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}

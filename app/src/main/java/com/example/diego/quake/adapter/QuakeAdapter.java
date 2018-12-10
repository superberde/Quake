package com.example.diego.quake.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.diego.quake.DetailActivity;
import com.example.diego.quake.R;
import com.example.diego.quake.database.QuakeEntry;
import com.example.diego.quake.databinding.QuakeListItemBinding;
import com.example.diego.quake.utils.QuakeAdapterUtils;

import java.util.Date;
import java.util.List;

public class QuakeAdapter extends RecyclerView.Adapter<QuakeAdapter.ViewHolder> {

    private static final String TAG = QuakeAdapter.class.getSimpleName();

    private static final String LOCATION_SEPARATOR = " of ";
    private static final String MAGNITUDE_TRANSITION_NAME = "magnitude-transition-name";

    private Context mContext;
    private List<QuakeEntry> mFeaturesBeanList;

    private QuakeListItemBinding mBinding;

    public QuakeAdapter(Context context, List<QuakeEntry> featuresBeanList) {
        mContext = context;
        mFeaturesBeanList = featuresBeanList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int position) {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        mBinding = DataBindingUtil.inflate(layoutInflater, R.layout.quake_list_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(mBinding.getRoot());
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {

        // Current earthquake
        QuakeEntry currentQuake = mFeaturesBeanList.get(position);

        // Magnitude
        final double magnitude = currentQuake.getMagnitude();
        mBinding.magnitude.setText(QuakeAdapterUtils.formatMagnitude(magnitude));

        // Date
        long time = currentQuake.getTime();
        Date date = new Date(time);

        // Formatted date
        String dateFormatted = QuakeAdapterUtils.formatDate(date);
        mBinding.date.setText(dateFormatted);

        // Formatted time
        String timeFormatted = QuakeAdapterUtils.formatTime(date);
        mBinding.time.setText(timeFormatted);

        // GradientDrawable
        GradientDrawable gradientDrawable = (GradientDrawable) mBinding.magnitude.getBackground();
        final int magnitudeColor = QuakeAdapterUtils.getMagnitudeColor(magnitude, mContext);
        gradientDrawable.setColor(magnitudeColor);

        // Location
        String originalLocation = currentQuake.getPlace();

        String primaryLocation;
        String locationOffset;

        // Check whether the originalLocation string contains the " of " text
        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            // Split the string into different parts (as an array of Strings)
            // based on the " of " text. We expect an array of 2 Strings, where
            // the first String will be "5km N" and the second String will be "Cairo, Egypt".
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            // Location offset should be "5km N " + " of " --> "5km N of"
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            // Primary location should be "Cairo, Egypt"
            primaryLocation = parts[1];
        } else {
            // Otherwise, there is no " of " text in the originalLocation string.
            // Hence, set the default location offset to say "Near the".
            locationOffset = mContext.getString(R.string.near_the);
            // The primary location will be the full location string "Pacific-Antarctic Ridge".
            primaryLocation = originalLocation;
        }

        mBinding.primaryLocation.setText(primaryLocation);
        mBinding.locationOffset.setText(locationOffset);

        // Shared Elements Transition
        ViewCompat.setTransitionName(mBinding.magnitude, MAGNITUDE_TRANSITION_NAME);

        // URL
        final String url = currentQuake.getLink();

        // Coordinates
        final List<Double> coordinates = currentQuake.getCoordinates();
        final double lat = coordinates.get(1);
        final double lng = coordinates.get(0);
        final double depth = coordinates.get(2);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_MAG, magnitude);
                intent.putExtra(DetailActivity.EXTRA_COLOR, magnitudeColor);
                intent.putExtra(DetailActivity.EXTRA_URL, url);
                intent.putExtra(DetailActivity.EXTRA_LAT, lat);
                intent.putExtra(DetailActivity.EXTRA_LNG, lng);
                intent.putExtra(DetailActivity.EXTRA_DEPTH, depth);
                intent.putExtra(DetailActivity.EXTRA_TRANSITION_NAME, ViewCompat.getTransitionName(mBinding.magnitude));

                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        (Activity) mContext,
                        mBinding.magnitude,
                        ViewCompat.getTransitionName(mBinding.magnitude)
                );

                mContext.startActivity(intent, activityOptionsCompat.toBundle());
                Log.d(TAG, "onClick: ");
            }
        });

        // Right-click support on Chrome OS
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            viewHolder.itemView.setOnContextClickListener(new View.OnContextClickListener() {
                @Override
                public boolean onContextClick(View v) {
                    // to display context click options
                    return true;
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mFeaturesBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

//        private TextView mMagnitudeTextView;
//        private TextView mDateTextView;
//        private TextView mTimeTextView;
//        private TextView mPrimaryLocationTextView;
//        private TextView mLocationOffsetTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

//            mMagnitudeTextView = itemView.findViewById(R.id.magnitude);
//            mDateTextView = itemView.findViewById(R.id.date);
//            mTimeTextView = itemView.findViewById(R.id.time);
//            mPrimaryLocationTextView = itemView.findViewById(R.id.primary_location);
//            mLocationOffsetTextView = itemView.findViewById(R.id.location_offset);

        }
    }
}

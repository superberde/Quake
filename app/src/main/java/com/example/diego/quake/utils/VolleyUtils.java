package com.example.diego.quake.utils;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleyUtils {

    private static VolleyUtils mOurInstance;
    private static Context sContext;
    private RequestQueue mRequestQueue;

    public VolleyUtils(Context context) {
        sContext = context;
        mRequestQueue = getRequestQueue();
    }

    private RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(sContext.getApplicationContext());
        }
        return mRequestQueue;
    }

    public static synchronized VolleyUtils getInstance(Context context) {
        if (mOurInstance == null) {
            mOurInstance = new com.example.diego.quake.utils.VolleyUtils(context);
        }
        return mOurInstance;
    }

    public<T> void addToRequestQueue(Request<T> request, Object tag) {
        request.setTag(tag);
        getRequestQueue().add(request);
    }

    public void cancelPendingRequests(Object tag){
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

}


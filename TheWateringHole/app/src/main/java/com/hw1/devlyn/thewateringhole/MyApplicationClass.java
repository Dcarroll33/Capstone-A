package com.hw1.devlyn.thewateringhole;

import android.app.Application;

import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by Devlyn on 4/8/2015.
 */
public class MyApplicationClass extends Application {
    public static GoogleApiClient mGoogleApiClient;

    public static GoogleApiClient getClient() {
        return mGoogleApiClient;
    }

    public static void setClient(GoogleApiClient newGoogleApiClient) {
        if(mGoogleApiClient == null) {
            mGoogleApiClient = newGoogleApiClient;
        }
    }
}

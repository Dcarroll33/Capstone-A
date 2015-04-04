package com.hw1.devlyn.thewateringhole;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class SplashActivity extends Activity {

    Handler handler;

    /*Assigns five second splash screen display time to the splash screen.*/
    static final int DELAY = 5000;

    /*Assigns the nextActivity as the login screen*/
    Class nextActivity = Login_Screen.class;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    public void onStart(){
        super.onStart();
        handler = new Handler();
        handler.postDelayed(r,DELAY);
    }

    Runnable r = new Runnable(){
      public void run() {
          gotoNextScreen();
      }
    };

    /*Method to fire the next activity after the splash screen displays.*/
    public void gotoNextScreen() {
        Intent nextScreen = new Intent(this, nextActivity);
        this.startActivity(nextScreen);
    }
}

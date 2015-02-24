package com.hw1.devlyn.thewateringhole;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Friends extends Activity implements View.OnClickListener {

    Button locateFriends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);


    locateFriends = (Button) this.findViewById(R.id.Locate_Friends);

    locateFriends.setOnClickListener(this);
}

    public void onClick(View v) {

        if (v == locateFriends) {
            Intent locateEvents = new Intent(this, Locate_Friends.class);

            Button b = (Button) v;
            this.startActivity(locateEvents);

        }
    }
}

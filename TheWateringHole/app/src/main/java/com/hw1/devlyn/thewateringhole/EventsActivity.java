package com.hw1.devlyn.thewateringhole;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class EventsActivity extends Activity implements View.OnClickListener {

    Button locate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        locate = (Button) this.findViewById(R.id.events_btn);

        locate.setOnClickListener(this);
    }

    public void onClick(View v) {

        if (v == locate) {
            Intent locateEvents = new Intent(this, LocateEvents.class);

            Button b = (Button) v;
            this.startActivity(locateEvents);

        }
    }
}

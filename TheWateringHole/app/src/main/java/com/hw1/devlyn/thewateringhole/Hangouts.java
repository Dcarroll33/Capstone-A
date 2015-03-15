package com.hw1.devlyn.thewateringhole;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Hangouts extends Activity implements View.OnClickListener {

    Button startHangout;

    Button hangoutsNearby;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangouts);

        startHangout = (Button) this.findViewById(R.id.hangouts_nrby_btn);
        hangoutsNearby = (Button) this.findViewById(R.id.start_btn);

        startHangout.setOnClickListener(this);
        hangoutsNearby.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v == startHangout) {
            Intent startHangout = new Intent(this, HangoutSetupActivity.class);

            Button b = (Button) v;
            this.startActivity(startHangout);
            if (v == hangoutsNearby) {
                Intent hangoutsNearby = new Intent(this, LocateHangoutActivity.class);

                Button c = (Button) v;
                this.startActivity(hangoutsNearby);
            }
        }
    }



}

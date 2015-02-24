package com.hw1.devlyn.thewateringhole;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class LocateHangoutActivity extends Activity implements View.OnClickListener {

    Button joinHangout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locate_hangout);

        joinHangout = (Button) this.findViewById(R.id.Join_Hangout);

        joinHangout.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v == joinHangout) {
            Intent joinHangout = new Intent(this, HangoutPageActivity.class);

            Button b = (Button) v;
            this.startActivity(joinHangout);

        }
    }



}

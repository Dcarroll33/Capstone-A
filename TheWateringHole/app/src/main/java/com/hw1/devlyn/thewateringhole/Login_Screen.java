package com.hw1.devlyn.thewateringhole;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.IntentSender.SendIntentException;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;

import java.io.IOException;


public class Login_Screen extends Activity implements View.OnClickListener {

    /* Request code used to invoke sign in user interactions. */
    private static final int RC_SIGN_IN = 0;

    /* Client used to interact with Google APIs. */

    private boolean mIntentInProgress;
    /**
     * True if the sign-in button was clicked.  When true, we know to resolve all
     * issues preventing sign-in without waiting.
     */
    private boolean mSignInClicked;

    private String token = null;

/*Field declarations for the buttons login and register*/
    Button login;

    Button register;
    EditText userNameText;
    EditText userNamePass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__screen);

        /*Assigns the login and register button to the buttons in the XML layout by their ID's*/
        login = (Button) this.findViewById(R.id.login_button);
        register = (Button) this.findViewById(R.id.register_button);
        userNameText = (EditText) this.findViewById(R.id.userNameText);
        userNamePass = (EditText) this.findViewById(R.id.userNamePass);

        /*Runs the listener for button clicks in this instance for login and register*/
        login.setOnClickListener(this);
        register.setOnClickListener(this);


    }

    @Override
    protected void onStart() {
        super.onStart();
        //mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    /*This method is used to listen for user clicks on the login & register buttons that are
        displayed on the screen*/
    public void onClick(View view) {
        /*Checks to see if the click is the login else if the click is on the register button*/
        if (view == login) {
            Intent login = new Intent(this, MainActivity.class );

            this.startActivity(login);

        }else if (view == register) {
            Intent register = new Intent(this, RegisterActivity.class);

            this.startActivity(register);
        }
    }
}

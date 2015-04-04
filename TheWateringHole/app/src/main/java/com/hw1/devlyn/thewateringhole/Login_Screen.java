package com.hw1.devlyn.thewateringhole;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Login_Screen extends Activity implements View.OnClickListener {

/*Field declarations for the buttons login and register*/
    Button login;

    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__screen);

        /*Assigns the login and register button to the buttons in the XML layout by their ID's*/
        login = (Button) this.findViewById(R.id.login_button);
        register = (Button) this.findViewById(R.id.register_button);

        /*Runs the listener for button clicks in this instance for login and register*/
        login.setOnClickListener(this);
        register.setOnClickListener(this);

    }

    /*This method is used to listen for user clicks on the login & register buttons that are
        displayed on the screen*/
    public void onClick(View v) {
        /*Checks to see if the click is the login else if the click is on the register button*/
        if (v == login) {
            Intent login = new Intent(this, MainActivity.class );

            Button b = (Button) v;
            this.startActivity(login);
        }else if (v == register) {
            Intent register = new Intent(this, Register.class);

            Button r = (Button) v;
            this.startActivity(register);
        }
    }

}

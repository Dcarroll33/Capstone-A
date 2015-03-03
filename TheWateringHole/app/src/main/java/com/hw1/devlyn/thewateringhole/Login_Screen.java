package com.hw1.devlyn.thewateringhole;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Login_Screen extends Activity implements View.OnClickListener {


    Button login;

    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__screen);

        login = (Button) this.findViewById(R.id.login);
        register = (Button) this.findViewById(R.id.register);

        login.setOnClickListener(this);
        register.setOnClickListener(this);

    }

    public void onClick(View v) {
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

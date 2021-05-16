package com.example.backposturecorrector;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginError extends Activity implements View.OnClickListener {

    TextView RegisterLink, LoginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginerror);

        RegisterLink = (TextView) findViewById(R.id.RegisterLink);
        RegisterLink.setOnClickListener(this);

        LoginLink = (TextView) findViewById(R.id.LoginLink);
        LoginLink.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.RegisterLink:
                startActivity(new Intent(this, Register.class));
                break;
            case R.id.LoginLink:
                startActivity(new Intent(this, Login.class));
        }
    }
}

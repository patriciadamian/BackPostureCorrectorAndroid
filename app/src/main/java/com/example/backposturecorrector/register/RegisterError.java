package com.example.backposturecorrector.register;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.backposturecorrector.R;
import com.example.backposturecorrector.login.LoginActivity;
import com.example.backposturecorrector.register.RegisterActivity;

public class RegisterError extends Activity implements View.OnClickListener {

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
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.LoginLink:
                startActivity(new Intent(this, LoginActivity.class));
        }
    }
}

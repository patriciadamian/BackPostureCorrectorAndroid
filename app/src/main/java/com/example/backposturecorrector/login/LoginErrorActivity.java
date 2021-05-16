package com.example.backposturecorrector.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.backposturecorrector.R;
import com.example.backposturecorrector.register.RegisterActivity;

public class LoginErrorActivity extends Activity implements View.OnClickListener {

    TextView registerLink, loginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginerror);

        registerLink = findViewById(R.id.RegisterLink);
        registerLink.setOnClickListener(this);

        loginLink = findViewById(R.id.LoginLink);
        loginLink.setOnClickListener(this);
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

package com.example.backposturecorrector.register;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.backposturecorrector.R;
import com.example.backposturecorrector.login.LoginActivity;

public class RegisterError extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginerror);

        TextView registerLink = findViewById(R.id.RegisterLink);
        registerLink.setOnClickListener(this);

        TextView loginLink = findViewById(R.id.LoginLink);
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

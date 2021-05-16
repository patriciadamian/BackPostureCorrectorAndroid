package com.example.backposturecorrector;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.backposturecorrector.services.LoginService;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button buttonLogin;
    EditText editTextEmail, editTextPassword;
    TextView RegisterLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(this);

        RegisterLink = (TextView) findViewById(R.id.RegisterLink);
        RegisterLink.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.RegisterLink:
                startActivity(new Intent(this, Register.class));
                break;

            case R.id.buttonLogin:
                LoginService loginService = new LoginService();
                Context context = getApplicationContext();
                CharSequence text = "Logged in successfully!";
                int duration = Toast.LENGTH_SHORT;
                String loginUrl = "https://back-posture-corrector.herokuapp.com";

                try {
                    String username = editTextEmail.getText().toString();
                    String password = editTextPassword.getText().toString();
                    String response = loginService.login(loginUrl, username, password);
                    if (response == null) {
                        startActivity(new Intent(this, LoginError.class));
                    } else {
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        startActivity(new Intent(this, UserForm.class));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }
}

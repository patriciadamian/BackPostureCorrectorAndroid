package com.example.backposturecorrector.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.backposturecorrector.R;
import com.example.backposturecorrector.register.RegisterActivity;
import com.example.backposturecorrector.UserForm;
import com.example.backposturecorrector.client.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextEmail, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        Button buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(this);

        TextView registerLink = findViewById(R.id.RegisterLink);
        registerLink.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.RegisterLink:
                startActivity(new Intent(this, RegisterActivity.class));
                break;

            case R.id.buttonLogin:
                try {
                    Context context = getApplicationContext();
                    LoginApi loginApi = ApiClient.getClient().create(LoginApi.class);

                    String email = editTextEmail.getText().toString();
                    String password = editTextPassword.getText().toString();
                    Call<LoginResponse> call = loginApi.login(new LoginResponse(email, password));

                    call.enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            if (response.isSuccessful()) {
                                CharSequence text = "Logged in successfully!";
                                int duration = Toast.LENGTH_SHORT;
                                Toast toast = Toast.makeText(context, text, duration);
                                LoginResponse loginResponse = response.body();
                                System.out.println(loginResponse);
                                toast.show();
                                startActivity(new Intent(context, UserForm.class));
                            } else {
                                startActivity(new Intent(context, LoginErrorActivity.class));
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            startActivity(new Intent(context, LoginErrorActivity.class));
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}

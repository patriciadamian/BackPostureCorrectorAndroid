package com.example.backposturecorrector.user.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.backposturecorrector.R;
import com.example.backposturecorrector.Session;
import com.example.backposturecorrector.HomeActivity;
import com.example.backposturecorrector.calibration.StartCalibrationActivity;
import com.example.backposturecorrector.client.ApiClient;
import com.example.backposturecorrector.login.LoginActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.backposturecorrector.Session.IS_USER_PROFILE_COMPLETE;

public class UserFormActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextAge, editTextWeight, editTextHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userform);

        editTextAge = findViewById(R.id.editTextAge);
        editTextWeight = findViewById(R.id.editTextWeight);
        editTextHeight = findViewById(R.id.editTextHeight);

        Button buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buttonSubmit) {
            try {
                Context context = getApplicationContext();
                UserProfileApi userProfileApi = ApiClient.getClient().create(UserProfileApi.class);

                Long id = Session.getId();
                short age = Short.parseShort(editTextAge.getText().toString());
                short weight = Short.parseShort(editTextWeight.getText().toString());
                short height = Short.parseShort(editTextHeight.getText().toString());

                Call<UserProfileResponse> call = userProfileApi.userProfile(Session.TOKEN, new UserProfileResponse(id, age, weight, height));

                call.enqueue(new Callback<UserProfileResponse>() {
                    @Override
                    public void onResponse(Call<UserProfileResponse> call, Response<UserProfileResponse> response) {
                        if (response.isSuccessful()) {
                            CharSequence text = "User profile updated successfully!";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                            IS_USER_PROFILE_COMPLETE = true;
                            if (Session.IS_CALIBRATION_COMPLETE) {
                                startActivity(new Intent(context, HomeActivity.class));
                            } else {
                                startActivity(new Intent(context, StartCalibrationActivity.class));
                            }
                        } else {
                            startActivity(new Intent(context, LoginActivity.class));
                        }
                    }

                    @Override
                    public void onFailure(Call<UserProfileResponse> call, Throwable t) {
                        startActivity(new Intent(context, LoginActivity.class));
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

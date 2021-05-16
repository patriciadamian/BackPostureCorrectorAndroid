package com.example.backposturecorrector;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class UserForm extends AppCompatActivity implements View.OnClickListener {

    Button buttonSubmit;
    EditText editTextAge, editTextWeight, editTextHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userform);

        editTextAge = (EditText) findViewById(R.id.editTextAge);
        editTextWeight = (EditText) findViewById(R.id.editTextWeight);
        editTextHeight = (EditText) findViewById(R.id.editTextHeight);

        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);
        buttonSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}

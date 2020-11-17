package com.example.uberclone;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class PhoneNumberActivity extends AppCompatActivity {
    EditText editText;

    public void goLogin(View view) {
        String number = editText.getText().toString();

        if(number.length() != 10){
            editText.setError("Enter a valid number");
            editText.requestFocus();
            return;
        }

        String phonenumber = "+91" + number;
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("phonenumber", phonenumber);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        editText = findViewById(R.id.editTextPhone);
    }
}
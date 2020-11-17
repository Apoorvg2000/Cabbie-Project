package com.example.uberclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class PaymentActivity extends AppCompatActivity {

    ProgressBar progressBar;
    EditText amount;

    public void payAmount(View view) {
        progressBar.setVisibility(View.VISIBLE);

        new CountDownTimer(5000,5000){

            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                Toast.makeText(PaymentActivity.this,"Payment of rupees " + amount.getText().toString() + " successful",Toast.LENGTH_SHORT).show();
                finish();

            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        setTitle("Payments");
        progressBar = findViewById(R.id.progressBar2);
        amount = findViewById(R.id.editTextAmount);
        progressBar.setVisibility(View.INVISIBLE);
    }
}
package com.example.e_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.e_app.R;

public class WelcomeActivity extends AppCompatActivity {

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        progressBar=findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
    }

    public void login(View view)
    {
        startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
    }
    public void register(View view)
    {
        startActivity(new Intent(WelcomeActivity.this, RegistrationActivity.class));
    }
}
package com.example.e_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.e_app.API.API;
import com.example.e_app.R;
import com.example.e_app.utility.AgriculturePortalConstant;
//import com.sunbeam.agricultureportalapp.LoginActivity;
//import com.sunbeam.agricultureportalapp.R;
//import com.sunbeam.agricultureportalapp.api.API;
//import com.sunbeam.agricultureportalapp.utility.AgriculturePortalConstant;

public class SplashActivity extends AppCompatActivity {

    ImageView imageSplash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imageSplash= findViewById(R.id.imageSplash);
//        imageSplash.startAnimation(AnimationUtils.loadAnimation(this,R.anim.zoom));
        Glide.with(this).load(API.BASE_URL+"/product/images/"+"1708068023238-tomato").into(imageSplash);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    if(getSharedPreferences(AgriculturePortalConstant.SHARED_PREFERENCE_FILE_NAME,MODE_PRIVATE)
                            .getBoolean(AgriculturePortalConstant.LOGIN_STATUS,false))
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    else
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));

                    finish();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
    }

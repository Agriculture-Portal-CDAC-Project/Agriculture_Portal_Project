package com.example.e_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_app.API.RetrofitClient;
import com.example.e_app.R;
import com.example.e_app.entity.User;
import com.example.e_app.utility.AgriculturePortalConstant;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button signIn;
    EditText editEmail,editPassword;

    CheckBox checkboxRememberMe;
    TextView signUp;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signIn = findViewById(R.id.login_btn);
        editEmail = findViewById(R.id.editEmail);
        editPassword=findViewById(R.id.editPassword);
        checkboxRememberMe=findViewById(R.id.checkboxRememberMe);
        signUp=findViewById(R.id.sign_up);

        progressBar = findViewById(R.id.progressbar);

        progressBar.setVisibility(View.GONE);


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                User user = new User();

                user.setEmail(editEmail.getText().toString());
                user.setPassword(editPassword.getText().toString());

                Log.e("user Details", user.toString());

                if(checkboxRememberMe.isChecked()){
                    Log.e("user Details", "inside checkbox checked");
                    getSharedPreferences(AgriculturePortalConstant.SHARED_PREFERENCE_FILE_NAME,MODE_PRIVATE)
                            .edit()
                            .putBoolean(AgriculturePortalConstant.LOGIN_STATUS,true)
                            .apply();
                }
                RetrofitClient.getInstance().getApi().loginUser(user).enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {


                        if(response.body().get("status").getAsString().equals("success")){
                            JsonArray array = response.body().get("data").getAsJsonArray();
                            if(array.size()!=0){
                                int id = array.get(0).getAsJsonObject().get("id").getAsInt();
                                getSharedPreferences(AgriculturePortalConstant.SHARED_PREFERENCE_FILE_NAME,MODE_PRIVATE)
                                        .edit()
                                        .putInt(AgriculturePortalConstant.USER_ID,id)
                                        .apply();
                                    Log.e("s : ",id+" shared pref");

                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                progressBar.setVisibility(View.VISIBLE);
                                finish();
                            }
                            else {
                                Toast.makeText(LoginActivity.this, "email or password is wrong", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "Something went wrong at the Login", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                });

                //after successful data loading set this to.... progressBar.setVisibility(View.GONE);
//                startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                progressBar.setVisibility(View.GONE);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
            }
        });

    }
}
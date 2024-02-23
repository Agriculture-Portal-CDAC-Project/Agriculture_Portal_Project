package com.example.e_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_app.API.RetrofitClient;
import com.example.e_app.R;
import com.example.e_app.entity.User;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {

    Button btnSignIn;
    EditText editFirstName,editLastName,editEmail,editPassword, editAddress, editMobile, editDob;

    TextView signIn;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        btnSignIn = findViewById(R.id.btnSignIn);
        editFirstName=findViewById(R.id.editFirstName);
        editLastName=findViewById(R.id.editLastName);
        editEmail=findViewById(R.id.editEmail);
        editPassword=findViewById(R.id.editPassword);
        editAddress=findViewById(R.id.editAddress);
        editMobile=findViewById(R.id.editMobile);
        editDob=findViewById(R.id.editDob);

        signIn=findViewById(R.id.sign_in);

        progressBar=findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });


    }

    public void signIn(View view)
    {

        progressBar.setVisibility(View.VISIBLE);
        User user = new User();

        user.setFirst_name(editFirstName.getText().toString());
        user.setLast_name(editLastName.getText().toString());
        user.setEmail(editEmail.getText().toString());
        user.setPassword(editPassword.getText().toString());
        user.setAddress(editAddress.getText().toString());
        user.setMobile(editMobile.getText().toString());
        user.setDob(editDob.getText().toString());
       // user.setRoll(editRoll.getText().toString());

        Log.e("user Details" , user.toString());
        if(user.getFirst_name().equals(" "))
        {
            Toast.makeText(this, "please enter first name!", Toast.LENGTH_SHORT).show();
        }
        else if (user.getLast_name().equals(" "))
        {
            Toast.makeText(this, "please enter last name!", Toast.LENGTH_SHORT).show();
        }
        else if (user.getEmail().equals("")|| user.getPassword().equals(" ")) {
            Toast.makeText(this, "email and password can no be empty!!!", Toast.LENGTH_SHORT).show();

        }
        else if (user.getAddress().equals(" "))
        {
            Toast.makeText(this, "plese enter address!", Toast.LENGTH_SHORT).show();
        }
        else if (user.getMobile().equals(" "))
        {
            Toast.makeText(this, "plese enter mobile number!", Toast.LENGTH_SHORT).show();
        }
        else {
            RetrofitClient.getInstance().getApi().registerUser(user).enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    if(response.body().get("status").getAsString().equals("success"))
                    {
                        finish();
                    }
                    else {
                        if (response.body().get("error").getAsJsonObject().get("errno").getAsInt() == 1062)
                            Toast.makeText(RegistrationActivity.this, "email already exists", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {

                    Toast.makeText(RegistrationActivity.this, "Something went Wrong while user registration !!!", Toast.LENGTH_SHORT).show();
                }
            });
        }



        //before navigation do this so it will disappear after login .... progressBar.setVisibility(View.GONE);
    }
}
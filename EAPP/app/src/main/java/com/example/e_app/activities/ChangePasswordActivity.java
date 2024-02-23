package com.example.e_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.e_app.API.RetrofitClient;
import com.example.e_app.R;
import com.example.e_app.entity.User;
import com.example.e_app.utility.AgriculturePortalConstant;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity {

    Button changePassBtn;

    EditText editPassword,editConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        changePassBtn = findViewById(R.id.savebtn);
        editPassword=findViewById(R.id.editPassword);
        editConfirmPassword=findViewById(R.id.editConfirmPassword);

        changePassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = editPassword.getText().toString();
                String confirmedPassword = editConfirmPassword.getText().toString();

               if(password.equals("") || confirmedPassword.equals(""))
               {
                   Toast.makeText(ChangePasswordActivity.this, "Both Fields Are Mandatory!!", Toast.LENGTH_SHORT).show();
               }
               else {
                   if ((password.equals(confirmedPassword)) && !password.equals(""))
                   {
                       //change the password if both are same
//                       Toast.makeText(ChangePasswordActivity.this, "Both are same", Toast.LENGTH_SHORT).show();
                       int id= getSharedPreferences(AgriculturePortalConstant.SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE).getInt(AgriculturePortalConstant.USER_ID,0);

                       User user = new User();
                       user.setId(id);
                       user.setPassword(password);

                       RetrofitClient.getInstance().getApi().changePassword(user).enqueue(new Callback<JsonObject>() {
                           @Override
                           public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                               if(response.body().get("status").getAsString().equals("success")){
                                   Toast.makeText(ChangePasswordActivity.this, "Password changed Successfully!!", Toast.LENGTH_SHORT).show();
                                   new Thread(new Runnable() {
                                       @Override
                                       public void run() {
                                           try {
                                               Thread.sleep(1200);
                                               editPassword.setText("");
                                               editConfirmPassword.setText("");
                                               finish();
                                           } catch (InterruptedException e) {
                                               throw new RuntimeException(e);
                                           }

                                       }
                                   }).start();
                               }
                           }

                           @Override
                           public void onFailure(Call<JsonObject> call, Throwable t) {
                               Toast.makeText(ChangePasswordActivity.this, "something went wrong while changing password!!", Toast.LENGTH_SHORT).show();
                           }
                       });
                   }
                   else
                   {
                       Toast.makeText(ChangePasswordActivity.this, "Both are not  same", Toast.LENGTH_SHORT).show();
                   }
               }






//                Toast.makeText(ChangePasswordActivity.this, "Changed Password Successfully!!", Toast.LENGTH_SHORT).show();
            }
        });

    }


}
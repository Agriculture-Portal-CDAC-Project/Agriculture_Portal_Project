package com.example.e_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class EditProfileActivity extends AppCompatActivity {
    EditText editFname,editLname,editEmail,editDob,editMobile,editAddress;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        user = (User) getIntent().getSerializableExtra("user");

       // Toast.makeText(this, ""+user, Toast.LENGTH_SHORT).show();
        editFname=findViewById(R.id.editFname);
        editLname=findViewById(R.id.editLname);
        editEmail=findViewById(R.id.editEmail);
        editDob=findViewById(R.id.editDob);
        editMobile=findViewById(R.id.editMobile);
        editAddress=findViewById(R.id.editAddress);

        editFname.setText(user.getFirst_name());
        editLname.setText(user.getLast_name());
        editEmail.setText(user.getEmail());
        editDob.setText(user.getDob());
        editMobile.setText(user.getMobile());
        editAddress.setText(user.getAddress());



    }

    public void save(View view)
    {
//        Toast.makeText(this, "save clicked", Toast.LENGTH_SHORT).show();
//
//        Log.e("user Details" , user.toString());

        int id= getSharedPreferences(AgriculturePortalConstant.SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE).getInt(AgriculturePortalConstant.USER_ID,0);
        user.setId(id);
        user.setFirst_name(editFname.getText().toString());
        user.setLast_name(editLname.getText().toString());
        user.setEmail(editEmail.getText().toString());
        user.setDob(editDob.getText().toString());
        user.setMobile(editMobile.getText().toString());
        user.setAddress(editAddress.getText().toString());

        Log.e("i","in else part");
        Toast.makeText(this, ""+user, Toast.LENGTH_SHORT).show();

        RetrofitClient.getInstance().getApi().editProfile(user).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.body().get("status").getAsString().equals("success")){

                    Toast.makeText(EditProfileActivity.this, "data updated successfully!!", Toast.LENGTH_SHORT).show();
                    user.setFirst_name("");
                    user.setLast_name("");
                    user.setEmail("");
                    user.setDob("");
                    user.setMobile("");
                    user.setAddress("");
                }
                else {
                    Toast.makeText(EditProfileActivity.this, "mobile no should be unique", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(EditProfileActivity.this, "something went wrong while changing password!!", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
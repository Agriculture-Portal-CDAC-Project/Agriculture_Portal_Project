package com.example.e_app.ui.profile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.e_app.API.RetrofitClient;
import com.example.e_app.R;
import com.example.e_app.activities.ChangePasswordActivity;
import com.example.e_app.activities.EditProfileActivity;
import com.example.e_app.entity.Product;
import com.example.e_app.entity.User;
import com.example.e_app.utility.AgriculturePortalConstant;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment {


    EditText editFname,editLname,editEmail,editPassword,editDob,editMobile,editAddress;

    TextView username,changePasswordBtn;

    Activity context;

    ImageView editProfileBtn;

    User user = new User();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile,container,false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editFname=view.findViewById(R.id.editFname);
        editLname=view.findViewById(R.id.editLname);
        editEmail=view.findViewById(R.id.editEmail);
        editPassword=view.findViewById(R.id.editPassword);
        editDob=view.findViewById(R.id.editDob);
        editMobile=view.findViewById(R.id.editMobile);
        editAddress=view.findViewById(R.id.editAddress);
        editProfileBtn = view.findViewById(R.id.editProfileBtn);
        username = view.findViewById(R.id.username);
        changePasswordBtn=view.findViewById(R.id.changePasswordBtn);
        context= getActivity();

        getUserData();

        //editing profile
        editProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //going to editprofile activity
                //Toast.makeText(context, "image clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, EditProfileActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);

            }
        });


        //change password
        changePasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //call change password api
                Toast.makeText(getContext(), "Pasword cahnge clicked", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(context, ChangePasswordActivity.class));
            }
        });
    }

    public void getUserData(){
        int id=getContext().getSharedPreferences(AgriculturePortalConstant.SHARED_PREFERENCE_FILE_NAME,Context.MODE_PRIVATE).getInt(AgriculturePortalConstant.USER_ID,0);

        Log.e("profileid",id+"");

//        getSharedPreferences(MovieReviewConstants.SHARED_PREFERENCE_FILE_NAME,MODE_PRIVATE)
//                .getBoolean(MovieReviewConstants.LOGIN_STATUS,false)

                // public User(int id, String first_name, String last_name, String email, String password, String mobile, String dob, String address, String roll)
        RetrofitClient.getInstance().getApi().getUserProfile(id).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.e("onResponse",""+response.body());
                if(response.body().get("status").getAsString().equals("success")){
                    JsonArray array = response.body().get("data").getAsJsonArray();

                    Log.e("v",array.toString());
                    for(JsonElement element:array){
                        JsonObject object = element.getAsJsonObject();

                        Log.e("object",""+object);


                        user.setId(object.get("id").getAsInt());
                        user.setFirst_name(object.get("first_name").getAsString());
                        user.setLast_name(object.get("last_name").getAsString());
                        user.setEmail(object.get("email").getAsString());
                        user.setMobile(object.get("mobile").getAsString());
                        user.setDob(object.get("dob").getAsString());
                        user.setPassword(object.get("password").getAsString());
                        user.setAddress(object.get("address").getAsString());

    }

                    editFname.setText(""+user.getFirst_name());
                    editLname.setText(""+user.getLast_name());
                    editEmail.setText(""+user.getEmail());
                    editDob.setText(""+user.getDob());
                    editMobile.setText(""+user.getMobile());
                    editAddress.setText(""+user.getAddress());


                    username.setText(user.getFirst_name()+ " "+user.getLast_name());

                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("onFailure","onFailure");
                Toast.makeText(getContext(), "Something wwnt wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }


   



}
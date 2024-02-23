package com.example.e_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.e_app.activities.LoginActivity;
import com.example.e_app.utility.AgriculturePortalConstant;


public class LogoutFragment extends Fragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        getContext().getSharedPreferences(AgriculturePortalConstant.SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)
                .edit()
                .putBoolean(AgriculturePortalConstant.LOGIN_STATUS, false)
                .apply();
        getContext().getSharedPreferences(AgriculturePortalConstant.SHARED_PREFERENCE_FILE_NAME,Context.MODE_PRIVATE).edit().putInt(AgriculturePortalConstant.USER_ID,0).apply();
        getContext().startActivity(new Intent(getContext(), LoginActivity.class));
        //requireActivity().finish();
        //return inflater.inflate(R.layout.fragment_logout, container, false);
       return  null;
    }
}
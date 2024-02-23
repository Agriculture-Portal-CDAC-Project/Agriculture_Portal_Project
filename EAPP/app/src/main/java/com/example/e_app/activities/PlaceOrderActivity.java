package com.example.e_app.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.e_app.R;
import com.example.e_app.ui.home.HomeFragment;

public class PlaceOrderActivity extends AppCompatActivity {

    TextView textTotal, textQuantity;
    Button btnPlaceOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

//        textTotal = findViewById(R.id.textTotal);
//        textQuantity = findViewById(R.id.textQuantity);
        btnPlaceOrder = findViewById(R.id.btnPlaceOrder);


        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//               Intent intent = new Intent();
//                float total=getIntent().getFloatExtra("total",0);
//                int totalQuantity = getIntent().getIntExtra("totalQuantity",0);
//               //float total =  intent.getFloatExtra("total",0);
//               Log.e("PlaceOrderACtivity",totalQuantity+"  "+total);

                FragmentContainerView fragmentContainer = findViewById(R.id.nav_home);
                HomeFragment fragment = new HomeFragment();

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_home, fragment);
                transaction.addToBackStack(null);
                transaction.commit();


            }
        });
    }


}
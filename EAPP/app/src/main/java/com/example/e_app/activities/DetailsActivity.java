package com.example.e_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.e_app.API.API;
import com.example.e_app.R;
import com.example.e_app.entity.Product;
//import com.sunbeam.agricultureportalapp.R;
//import com.sunbeam.agricultureportalapp.api.API;
//import com.sunbeam.agricultureportalapp.entity.Product;

public class DetailsActivity extends AppCompatActivity {

    TextView textName,textPrice,textDescription;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        imageView =findViewById(R.id.imageView);
       textName = findViewById(R.id.textName);
       textPrice = findViewById(R.id.textPrice);
       textDescription = findViewById(R.id.textDescription);


        Product product = (Product) getIntent().getSerializableExtra("product");

        Log.e("pr",product.toString());

        Glide.with(this).load(API.BASE_URL+"/product/images/"+product.getImage()).into(imageView);

        textName.setText("Name : " + product.getName());
        textPrice.setText("Price : " + "₹ "+product.getPrice());
        textDescription.setText("Description : " + product.getDescription());

    }
}
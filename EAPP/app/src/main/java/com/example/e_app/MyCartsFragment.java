package com.example.e_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.e_app.API.RetrofitClient;
import com.example.e_app.activities.PlaceOrderActivity;
import com.example.e_app.adapter.CartAdapter;
import com.example.e_app.entity.Product;
import com.example.e_app.utility.AgriculturePortalConstant;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyCartsFragment extends Fragment {
    //Toolbar toolbar;
    RecyclerView recyclerView;
    List<Product> cartList;

    Button btnTotal,btnProceed;
    Product product;
    float intiTotal=0;
    CartAdapter cartAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_carts, container, false);
    }

    Activity context;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        toolbar = findViewById(R.id.toolBar);
//        setSupportActionBar(toolbar);
        recyclerView = view.findViewById(R.id.recyclerView);
        btnTotal = view.findViewById(R.id.btnTotal);
        btnProceed = view.findViewById(R.id.btnProceed);
        cartList = new ArrayList<>();


        cartAdapter= new CartAdapter(cartList,getContext(),btnTotal,btnProceed,intiTotal);
        recyclerView.setAdapter(cartAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        getCart();
        context=getActivity();

        //btnProceed.setOnClickListener(v -> startActivity(new Intent(context, PlaceOrderActivity.class)));


    }

    public void getCart()
    {
//        int u_id = PreferenceManager.getDefaultSharedPreferencesName(AgriculturePortalConstant.SHARED_PREFERENCE_FILE_NAME, MODE_PRIVATE )
//                .getInt(AgriculturePortalConstant.USER_ID,0);

//        SharedPreferences sharedPreferences = getContext().getPreferences(Context.MODE_PRIVATE);
        int u_id=getContext().getSharedPreferences(AgriculturePortalConstant.SHARED_PREFERENCE_FILE_NAME,Context.MODE_PRIVATE).getInt(AgriculturePortalConstant.USER_ID,0);
        // Reading from SharedPreferences
//        int u_id = sharedPreferences.getInt(AgriculturePortalConstant.USER_ID,0);

        Log.e("onResponse","user Id"+u_id);


        RetrofitClient.getInstance().getApi().getCart(u_id).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.e("onResponse",""+response.body());
                if(response.body().get("status").getAsString().equals("success")){
                    JsonArray array = response.body().get("data").getAsJsonArray();

                    Log.e("v",array.toString());


                    cartList.clear();
                    for(JsonElement element:array){
                        JsonObject object = element.getAsJsonObject();

                        Product product = new Product();
                        product.setId(object.get("p_id").getAsInt());
                        product.setName(object.get("name").getAsString());
                        product.setImage(object.get("image").getAsString());
                        //note price in db cant be null
                        product.setPrice(object.get("price").getAsFloat());


                        product.setDescription(object.get("description").getAsString());
                        product.setCat_id(object.get("cat_id").getAsInt());
                        product.setQty(object.get("qty").getAsInt());

                        cartList.add(product);
                    }
                    cartAdapter.notifyDataSetChanged();
                    for(Product p:cartList) {
                        intiTotal = intiTotal +  (p.getPrice() * 1);
                    }
                    //btnTotal.setText(intiTotal+"");
                    btnTotal.setText(Float.toString(intiTotal));
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
package com.example.e_app.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_app.API.RetrofitClient;
import com.example.e_app.R;
import com.example.e_app.adapter.ProductAdapter;
import com.example.e_app.databinding.FragmentHomeBinding;
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

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    RecyclerView recyclerView;
//    Toolbar toolbar;
    //756389
    List<Product> productList;
    Button firtilizer, pesticide,vegetable;


    ProductAdapter productAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home,container,false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        toolbar = findViewById(R.id.toolBar);
//        setSupportActionBar(toolbar);
        recyclerView = view.findViewById(R.id.recyclerView);
        firtilizer = view.findViewById(R.id.firtilizer);
        pesticide = view.findViewById(R.id.pesticide);
        vegetable = view.findViewById(R.id.vegetable);

//        int u_id = getSharedPreferences(AgriculturePortalConstant.SHARED_PREFERENCE_FILE_NAME,MODE_PRIVATE)
//                .getInt(AgriculturePortalConstant.USER_ID,0);
        int u_id=getContext().getSharedPreferences(AgriculturePortalConstant.SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE).getInt(AgriculturePortalConstant.USER_ID,0);

        Log.e("productList" , ""+u_id);


        productList = new ArrayList<>();
//        productList.add(new Product("Shampoo", 500, "description", "hi.jpg",20, 3));
//        productList.add(new Product("tomato", 700, "description", "hello.jpg",40, 2));


        //now set adapters
        productAdapter = new ProductAdapter(productList,getContext(),u_id);
        recyclerView.setAdapter(productAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));

        Log.e("productListActivity" , "inside product list");

        getAllProducts();

        firtilizer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllFirtilizer();
            }
        });

        pesticide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllPesticide();
            }
        });

        vegetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllVegetable();
            }
        });
    }


//    public void getPesticide(View view){
//        getAllPesticide();
//    }
//    public void getFirtilizer(View view){
//        getAllFirtilizer();
//    }
//    public void getVegetable(View view){
//        getAllVegetable();
//    }
    public void getAllProducts(){
        //calling backend api to get data

        RetrofitClient.getInstance().getApi().getAllProducts().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.e("onResponse",""+response.body());
                if(response.body().get("status").getAsString().equals("success")){
                    JsonArray array = response.body().get("data").getAsJsonArray();

                    Log.e("v",array.toString());


                    productList.clear();
                    for(JsonElement element:array){
                        JsonObject object = element.getAsJsonObject();

                        Product product = new Product();
                        product.setId(object.get("id").getAsInt());
                        product.setName(object.get("name").getAsString());
                        product.setImage(object.get("image").getAsString());
                        //note price in db cant be null
                        product.setPrice(object.get("price").getAsFloat());


                        product.setDescription(object.get("description").getAsString());
                        product.setCat_id(object.get("cat_id").getAsInt());
                        product.setQty(object.get("qty").getAsInt());

                        productList.add(product);
                    }
                    productAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("onFailure","onFailure");
                Toast.makeText(getContext(), "Something wwnt wrong", Toast.LENGTH_SHORT).show();
            }
        });



    }


    public void getAllPesticide(){
        //calling backend api to get data

        RetrofitClient.getInstance().getApi().getPesticide("pesticide").enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.e("onResponse",""+response.body());
                if(response.body().get("status").getAsString().equals("success")){
                    JsonArray array = response.body().get("data").getAsJsonArray();

                    Log.e("v",array.toString());


                    productList.clear();
                    for(JsonElement element:array){
                        JsonObject object = element.getAsJsonObject();

                        Product product = new Product();
                        product.setId(object.get("id").getAsInt());
                        product.setName(object.get("name").toString());
                        product.setImage(object.get("image").getAsString());
                        //note price in db cant be null
                        product.setPrice(object.get("price").getAsFloat());


                        product.setDescription(object.get("description").toString());
                        product.setCat_id(object.get("cat_id").getAsInt());
                        product.setQty(object.get("qty").getAsInt());

                        productList.add(product);
                    }
                    productAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("onFailure","onFailure");
                Toast.makeText(getContext(), "Something wwnt wrong", Toast.LENGTH_SHORT).show();
            }
        });



    }


    public void getAllFirtilizer(){
        //calling backend api to get data

        RetrofitClient.getInstance().getApi().getFirtilizer("insecticide").enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.e("onResponse",""+response.body());
                if(response.body().get("status").getAsString().equals("success")){
                    JsonArray array = response.body().get("data").getAsJsonArray();

                    Log.e("v",array.toString());


                    productList.clear();
                    for(JsonElement element:array){
                        JsonObject object = element.getAsJsonObject();

                        Product product = new Product();
                        product.setId(object.get("id").getAsInt());
                        product.setName(object.get("name").toString());
                        product.setImage(object.get("image").getAsString());
                        //note price in db cant be null
                        product.setPrice(object.get("price").getAsFloat());


                        product.setDescription(object.get("description").toString());
                        product.setCat_id(object.get("cat_id").getAsInt());
                        product.setQty(object.get("qty").getAsInt());

                        productList.add(product);
                    }
                    productAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("onFailure","onFailure");
                Toast.makeText(getContext(), "Something wwnt wrong", Toast.LENGTH_SHORT).show();
            }
        });



    }

    public void getAllVegetable(){
        //calling backend api to get data

        RetrofitClient.getInstance().getApi().getVegetables("vegetable").enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.e("onResponse",""+response.body());
                if(response.body().get("status").getAsString().equals("success")){
                    JsonArray array = response.body().get("data").getAsJsonArray();

                    Log.e("v",array.toString());


                    productList.clear();
                    for(JsonElement element:array){
                        JsonObject object = element.getAsJsonObject();

                        Product product = new Product();
                        product.setId(object.get("id").getAsInt());
                        product.setName(object.get("name").toString());
                        product.setImage(object.get("image").getAsString());
                        //note price in db cant be null
                        product.setPrice(object.get("price").getAsFloat());


                        product.setDescription(object.get("description").toString());
                        product.setCat_id(object.get("cat_id").getAsInt());
                        product.setQty(object.get("qty").getAsInt());

                        productList.add(product);
                    }
                    productAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("onFailure","onFailure");
                Toast.makeText(getContext(), "Something wwnt wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
////        return super.onCreateOptionsMenu(menu,);
//        // to check for if remeber me is checked then only show the logout menu button
//        menu.add("cart").setIcon(R.drawable.ic_my_carts).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
//
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        Intent intent = new Intent(this,ViewCartActivity.class);
//        startActivity(intent);
//
//
//        return super.onOptionsItemSelected(item);
//    }
    //    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }
}
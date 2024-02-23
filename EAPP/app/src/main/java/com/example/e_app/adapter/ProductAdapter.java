package com.example.e_app.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.e_app.API.API;
import com.example.e_app.API.RetrofitClient;
import com.example.e_app.R;
import com.example.e_app.activities.DetailsActivity;
import com.example.e_app.entity.Cart;
import com.example.e_app.entity.Product;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
//import com.sunbeam.agricultureportalapp.R;
//import com.sunbeam.agricultureportalapp.activities.DetailsActivity;
//import com.sunbeam.agricultureportalapp.api.API;
//import com.sunbeam.agricultureportalapp.api.RetrofitClient;
//import com.sunbeam.agricultureportalapp.entity.Cart;
//import com.sunbeam.agricultureportalapp.entity.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    List<Product> productList;
    Context context;
    Cart cart;
    int u_id;

    public ProductAdapter(List<Product> productList, Context context,int u_id) {
        this.productList = productList;
        this.context = context;
        this.u_id = u_id;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_list,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.textName.setText(product.getName());
        holder.textPrice.setText("₹ "+product.getPrice());
        Glide.with(context).load(API.BASE_URL+"/product/images/"+product.getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textName , textPrice;
        ImageView imageView;

        Button addCartBtn;

       public MyViewHolder(@NonNull View itemView) {
           super(itemView);
           textName = itemView.findViewById(R.id.textName);
           textPrice = itemView.findViewById(R.id.textPrice);
           imageView = itemView.findViewById(R.id.imageView);

           addCartBtn = itemView.findViewById(R.id.addCartBtn);

           //onclick going to detail activity
           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Product product = productList.get(getAdapterPosition());
                   Intent intent = new Intent(context, DetailsActivity.class);
                   intent.putExtra("product", product);
                   context.startActivity(intent);
               }
           });


           //add Product to cart
           addCartBtn.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Cart cart = new Cart();
                   Product product = productList.get(getAdapterPosition());
                   Log.e("productAdapter", "" + u_id);

                   float total = 1 * product.getPrice();
                   Log.e("productAdapter", "" + product.getId() + "  " + product.getQty() + "  " + product.getPrice() + "  " + total);
                   cart.setU_id(u_id);
                   cart.setP_id(product.getId());
                   cart.setQty(1);
                   cart.setPrice(product.getPrice());
                   cart.setTotal(total);

                   RetrofitClient.getInstance().getApi().addCart(cart).enqueue(new Callback<JsonObject>() {
                       @Override
                       public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                           if(response.body().get("status").getAsString().equals("success"))
                           {
//                               JsonArray jsonArray = response.body().getAsJsonArray("data");

                               if(response.body().getAsJsonObject("data").size()!=0)
                               {
                                   Toast.makeText(context, "product added to cart", Toast.LENGTH_SHORT).show();
                                   addCartBtn.setText("Added");
                               }

                           }
                           else {
                               Toast.makeText(context, "added !!", Toast.LENGTH_SHORT).show();
                           }
                       }

                       @Override
                       public void onFailure(Call<JsonObject> call, Throwable t) {

                           Toast.makeText(context, "something went wrong while adding cart!", Toast.LENGTH_SHORT).show();
                       }
                   });

               }

//       public void addCartCall(Product product) {
//            int u_id =
//            RetrofitClient.getInstance().getApi().addCart().enqueue(new Callback<JsonObject>() {
//                @Override
//                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//
//                }
//
//                @Override
//                public void onFailure(Call<JsonObject> call, Throwable t) {
//
//                }
//            });

//       }
           });
       }
    }
}



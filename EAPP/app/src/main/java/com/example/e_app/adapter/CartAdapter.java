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
import com.example.e_app.activities.HomeActivity;
import com.example.e_app.activities.MainActivity;
import com.example.e_app.activities.PlaceOrderActivity;
import com.example.e_app.entity.Cart;
import com.example.e_app.entity.Product;
import com.example.e_app.ui.home.HomeFragment;
import com.example.e_app.utility.AgriculturePortalConstant;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
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

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    List<Product> cartList;
    Context context;
    float intiTotal;
    Cart cart;
//    float intiTotal=0;
    Button btnTotal , btnProceed;




    public CartAdapter(List<Product> cartList, Context context ,Button btnTotal, Button btnProceed,Float intiTotal) {
        this.cartList = cartList;
        this.context = context;
        this.btnProceed = btnProceed;
        this.btnTotal = btnTotal;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_list, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Product product = cartList.get(position);
        holder.textName.setText(product.getName());
        holder.textPrice.setText("₹ " + product.getPrice());
        Glide.with(context).load(API.BASE_URL + "/product/images/" + product.getImage()).into(holder.imageView);

    }

    //@Override
//    public void onBindViewHolder(@NonNull ProductAdapter.MyViewHolder holder, int position) {
//          }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textName, textPrice , textQuantity;
        ImageView imageView;

        Button btnPlus, btnMinus;
        int quantity = 1;
        int quantityTotal = 0;
        float total = 0 ;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.textName);
            textPrice = itemView.findViewById(R.id.textPrice);
            imageView = itemView.findViewById(R.id.imageView);
            textQuantity = itemView.findViewById(R.id.textQuantity);
            btnPlus = itemView.findViewById(R.id.btnPlus);
            btnMinus = itemView.findViewById(R.id.btnMinus);
//            Product product = cartList.get(getAdapterPosition());
//            float total1 = product.getPrice() * 1;
//            btnTotal.setText(total1+"");

//            for (Product p:cartList) {
//                intiTotal = intiTotal +  p.getPrice() * Integer.parseInt(textQuantity.getText().toString());
//            }
             //calc();
//            for (Product p:cartList) {
//                Log.e("myproductList" , p.toString()+"");
//                intiTotal = intiTotal+ p.getPrice() * Integer.parseInt(textQuantity.getText().toString());
//            }
//            btnTotal.setText(String.valueOf(intiTotal));
            btnPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("plus::", "initiTotal :::"+intiTotal);
                    quantity++;
                    //Log.e("plus::", "quantity"+quantity);
                    textQuantity.setText(""+quantity);
                    Product product = cartList.get(getAdapterPosition());
//                    intiTotal = intiTotal + product.getPrice();
                   float Total = Float.parseFloat(btnTotal.getText().toString())+product.getPrice();

                    btnTotal.setText(Float.toString(Total));

                }
            });

            btnMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int u_id = context.getSharedPreferences(AgriculturePortalConstant.SHARED_PREFERENCE_FILE_NAME,Context.MODE_PRIVATE).getInt(AgriculturePortalConstant.USER_ID,0);

                    Product product = cartList.get(getAdapterPosition());
                    int p_id = product.getId();

                    Log.e("1st", intiTotal+"");
                    Log.e("1st", quantity+"");

                    Log.e("id's", u_id+"  "+p_id);

                    if(quantity>0)
                    {
                        quantity--;
                        if(quantity>0) {
                            textQuantity.setText("" + quantity);
                        }
                    }

                    if(quantity==0)
                    {

                        RetrofitClient.getInstance().getApi().deleteCartItem(p_id,u_id).enqueue(new Callback<JsonObject>() {
                            @Override
                            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                                if(response.body().get("status").getAsString().equals("success"))
                                {
                                    Log.e("onres", "delete cart :"+response.body());
                                    cartList.remove(product);

                                    float Total = Float.parseFloat(btnTotal.getText().toString()) - product.getPrice();
                                    btnTotal.setText(Float.toString(Total));
                                    Toast.makeText(context, "item Removed!", Toast.LENGTH_SHORT).show();
                                    if(cartList.size()==0)
                                    {
                                        context.startActivity(new Intent(context, MainActivity.class));
                                    }
                                    // getCart();
                                }
                                notifyDataSetChanged();
                            }

                            @Override
                            public void onFailure(Call<JsonObject> call, Throwable t) {
                                Toast.makeText(context, "cant Remove item!", Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                    else {

                        float Total = Float.parseFloat(btnTotal.getText().toString()) - product.getPrice();
                        btnTotal.setText(Float.toString(Total));
//                        intiTotal = intiTotal - product.getPrice();
//                        Log.e("2st", intiTotal+"");
//                        Log.e("2st", quantity+"");
//                        btnTotal.setText(intiTotal+"");
                    }
                    quantityTotal = quantityTotal  - quantity;




//                    quantity--;
//                    quantityTotal = quantityTotal  - quantity;
//                    if(quantity<=0)
//                    {
//                        textQuantity.setText(1+"");
//                        //calculatePrice();
//                    }
//                    else {
//                        textQuantity.setText(""+quantity);
//                        calculatePrice();
//                    }

                }
            });




            btnProceed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(cartList.size()!=0) {
                        int u_id = context.getSharedPreferences(AgriculturePortalConstant.SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE).getInt(AgriculturePortalConstant.USER_ID, 0);

                        RetrofitClient.getInstance().getApi().placeOrder(u_id).enqueue(new Callback<JsonObject>() {
                            @Override
                            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                                Log.e("placeOrder", response.body() + "");

                                if (response.body().get("status").getAsString().equals("success")) {
                                    Log.e("placeOrder", response.body() + "");
                                    Toast.makeText(context, "order placed", Toast.LENGTH_SHORT).show();
                                    //float cartDetails[] = {quantityTotal,total};
                                    Intent intent = new Intent(context, PlaceOrderActivity.class);
                                    //intent.putExtra("totalQuantity" ,quantityTotal );
                                    //intent.putExtra("total" ,intiTotal );
                                    context.startActivity(intent);
                                } else {
                                    Toast.makeText(context, "can't place order!!!!", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<JsonObject> call, Throwable t) {
                                Toast.makeText(context, "something went wrong while placing order!!!!", Toast.LENGTH_SHORT).show();

                            }
                        });
                    }else {
                        Toast.makeText(context, "Cart is empty!!", Toast.LENGTH_SHORT).show();
                    }


//                    float cartDetails[] = {quantityTotal,total};
//                    Intent intent = new Intent(context, PlaceOrderActivity.class);
//                    intent.putExtra("totalQuantity" ,quantityTotal );
//                    intent.putExtra("total" ,intiTotal );
//                    context.startActivity(intent);

                    Toast.makeText(context, "placeorder clicked", Toast.LENGTH_SHORT).show();
                }
            });


        }




//        public void calc()
//        {
//            for(Product p:cartList) {
//                Log.e("myproductList" , p.toString()+"");
//                intiTotal = intiTotal+ p.getPrice() * Integer.parseInt(textQuantity.getText().toString());
//            }
//
//            //intiTotal=Float.parseFloat(btnTotal.getText().toString());
//            //intiTotal=Float.valueOf(btnTotal.getText().toString());
//        }
        public void calculatePrice(){
//                int temp = Integer.parseInt(text1.getText().toString());
            Product product = cartList.get(getAdapterPosition());
            float total1 = product.getPrice() * 1;
            float price = product.getPrice();
            total = product.getPrice() * 1;
            if(quantityTotal<=0)
            {
                total =0;
            }
            else if(quantityTotal==1)
            {
                total = total1;
            }
            else {
                total = total+(price * 1);
                Log.e("total" , total+"");
            }


            btnTotal.setText(total+"");

        }


    }



//    class MyViewHolder extends RecyclerView.ViewHolder {
//
//        TextView textName, textPrice , textQuantity;
//        ImageView imageView;
//
//        Button btnPlus, btnMinus;
//        int quantity = 1;
//        int quantityTotal = 0;
//        float total = 0 ;
//        float intiTotal=0;
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//            textName = itemView.findViewById(R.id.textName);
//            textPrice = itemView.findViewById(R.id.textPrice);
//            imageView = itemView.findViewById(R.id.imageView);
//            textQuantity = itemView.findViewById(R.id.textQuantity);
//            btnPlus = itemView.findViewById(R.id.btnPlus);
//            btnMinus = itemView.findViewById(R.id.btnMinus);
//
////            Product product = cartList.get(getAdapterPosition());
////            float total1 = product.getPrice() * 1;
////            btnTotal.setText(total1+"");
//
//            calc();
//            btnTotal.setText(intiTotal+"");
//            btnPlus.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.e("plus::", "initiTotal :::"+intiTotal);
//                    quantity++;
//                    //Log.e("plus::", "quantity"+quantity);
//                    textQuantity.setText(""+quantity);
//                    Product product = cartList.get(getAdapterPosition());
//                    intiTotal = intiTotal + product.getPrice();
//                    btnTotal.setText(intiTotal+"");
//
//                }
//            });
//
//            btnMinus.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    int u_id = context.getSharedPreferences(AgriculturePortalConstant.SHARED_PREFERENCE_FILE_NAME,Context.MODE_PRIVATE).getInt(AgriculturePortalConstant.USER_ID,0);
//
//                   Product product = cartList.get(getAdapterPosition());
//                   int p_id = product.getId();
//
//                    Log.e("1st", intiTotal+"");
//                    Log.e("1st", quantity+"");
//
//                    Log.e("id's", u_id+"  "+p_id);
//
//                    if(quantity>0)
//                    {
//                        quantity--;
//                    }
//
//                    if(quantity==0)
//                    {
//
//                        RetrofitClient.getInstance().getApi().deleteCartItem(p_id,u_id).enqueue(new Callback<JsonObject>() {
//                            @Override
//                            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//                                if(response.body().get("status").getAsString().equals("success"))
//                                {
//                                    Log.e("onres", "delete cart :"+response.body());
//                                    cartList.remove(product);
//
//                                    intiTotal = intiTotal - product.getPrice();
//                                    Log.e("2st", intiTotal+"");
//                                    Log.e("2st", quantity+"");
//                                    btnTotal.setText(intiTotal+"");
//                                    Toast.makeText(context, "item Removed!", Toast.LENGTH_SHORT).show();
//                                    if(cartList.size()==0)
//                                    {
//                                        context.startActivity(new Intent(context, MainActivity.class));
//                                    }
//                                   // getCart();
//                                }
//                                notifyDataSetChanged();
//                            }
//
//                            @Override
//                            public void onFailure(Call<JsonObject> call, Throwable t) {
//                                Toast.makeText(context, "cant Remove item!", Toast.LENGTH_SHORT).show();
//
//                            }
//                        });
//                    }
//                    else {
//                        intiTotal = intiTotal - product.getPrice();
//                        Log.e("2st", intiTotal+"");
//                        Log.e("2st", quantity+"");
//                        btnTotal.setText(intiTotal+"");
//                    }
//                    quantityTotal = quantityTotal  - quantity;
//                    textQuantity.setText(""+quantity);
//
//
//
////                    quantity--;
////                    quantityTotal = quantityTotal  - quantity;
////                    if(quantity<=0)
////                    {
////                        textQuantity.setText(1+"");
////                        //calculatePrice();
////                    }
////                    else {
////                        textQuantity.setText(""+quantity);
////                        calculatePrice();
////                    }
//
//                }
//            });
//
//
//
//
//            btnProceed.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    if(cartList.size()!=0) {
//                        int u_id = context.getSharedPreferences(AgriculturePortalConstant.SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE).getInt(AgriculturePortalConstant.USER_ID, 0);
//
//                        RetrofitClient.getInstance().getApi().placeOrder(u_id).enqueue(new Callback<JsonObject>() {
//                            @Override
//                            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//
//                                Log.e("placeOrder", response.body() + "");
//
//                                if (response.body().get("status").getAsString().equals("success")) {
//                                    Log.e("placeOrder", response.body() + "");
//                                    Toast.makeText(context, "order placed", Toast.LENGTH_SHORT).show();
//                                    //float cartDetails[] = {quantityTotal,total};
//                                    Intent intent = new Intent(context, PlaceOrderActivity.class);
//                                    //intent.putExtra("totalQuantity" ,quantityTotal );
//                                    //intent.putExtra("total" ,intiTotal );
//                                    context.startActivity(intent);
//                                } else {
//                                    Toast.makeText(context, "can't place order!!!!", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//
//                            @Override
//                            public void onFailure(Call<JsonObject> call, Throwable t) {
//                                Toast.makeText(context, "something went wrong while placing order!!!!", Toast.LENGTH_SHORT).show();
//
//                            }
//                        });
//                    }else {
//                        Toast.makeText(context, "Cart is empty!!", Toast.LENGTH_SHORT).show();
//                    }
//
//
////                    float cartDetails[] = {quantityTotal,total};
////                    Intent intent = new Intent(context, PlaceOrderActivity.class);
////                    intent.putExtra("totalQuantity" ,quantityTotal );
////                    intent.putExtra("total" ,intiTotal );
////                    context.startActivity(intent);
//
//                    Toast.makeText(context, "placeorder clicked", Toast.LENGTH_SHORT).show();
//                }
//            });
//
//        }
//
//
//
//
//        public void calc()
//       {
//           for(Product p:cartList) {
//               Log.e("myproductList" , p.toString()+"");
//               intiTotal = intiTotal+ p.getPrice() * Integer.parseInt(textQuantity.getText().toString());
//           }
//
//       }
//        public void calculatePrice(){
////                int temp = Integer.parseInt(text1.getText().toString());
//            Product product = cartList.get(getAdapterPosition());
//            float total1 = product.getPrice() * 1;
//            float price = product.getPrice();
//            total = product.getPrice() * 1;
//            if(quantityTotal<=0)
//            {
//                total =0;
//            }
//            else if(quantityTotal==1)
//            {
//                total = total1;
//            }
//            else {
//                total = total+(price * 1);
//                Log.e("total" , total+"");
//            }
//
//
//            btnTotal.setText(total+"");
//
//        }
//
//
//    }
}


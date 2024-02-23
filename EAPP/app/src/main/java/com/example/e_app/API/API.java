package com.example.e_app.API;

import com.example.e_app.entity.Cart;
import com.example.e_app.entity.Product;
import com.example.e_app.entity.User;
import com.google.gson.JsonObject;
//import com.sunbeam.agricultureportalapp.entity.Cart;
//import com.sunbeam.agricultureportalapp.entity.Product;
//import com.sunbeam.agricultureportalapp.entity.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface API {

    public static final String BASE_URL = "http://192.168.43.144:9898";

    @POST("/users/register")
    public Call<JsonObject> registerUser(@Body User user);


    //profile section
    @GET("/users/{id}")
    public Call<JsonObject> getUserProfile(@Path("id") int id);
    @PUT("/users/changePassword")
    public Call<JsonObject> changePassword(@Body User user);

    @PUT("/users/editProfile")
    public Call<JsonObject> editProfile(@Body User user);


    @POST("/users/login")
    public Call<JsonObject> loginUser(@Body User user );

    @GET("/product/getAll")
    public Call<JsonObject> getAllProducts();

    @GET("/product/findByCategory/{name}")
    public Call<JsonObject> getVegetables(@Path("name") String name);


    @GET("/product/findByCategory/{name}")
    public Call<JsonObject> getPesticide(@Path("name") String name);

    @GET("/product/findByCategory/{name}")
    public Call<JsonObject> getFirtilizer(@Path("name") String name);

    @POST("/cart/addCart")//uid,pid,qty
    public Call<JsonObject> addCart(@Body Cart cart);

    @GET("/cart/getCart/{u_id}")//uid,pid,qty
    public Call<JsonObject> getCart(@Path("u_id") int u_id);

    @DELETE("/cart/deleteCart/{p_id}/{u_id}")
    public Call<JsonObject> deleteCartItem(@Path("p_id") int p_id,@Path("u_id") int u_id);

    @POST("/order/{u_id}")
    public Call<JsonObject> placeOrder(@Path("u_id") int u_id);



}

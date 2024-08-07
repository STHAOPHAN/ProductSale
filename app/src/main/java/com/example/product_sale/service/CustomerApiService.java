package com.example.product_sale.service;

import com.example.product_sale.config.AppConfig;
import com.example.product_sale.models.Customer;
import com.example.product_sale.models.Pet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CustomerApiService {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(AppConfig.DATABASE_URL)
            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                    .create()))
            .build();

    @GET("api/customers")
    Call<List<Customer>> getCustomers();
    @GET("api/customers")
    Call<List<Customer>> getCustomerByEmail(@Query("email") String email);

    @GET("api/customers")
    Call<List<Customer>> getCustomers(
            @Query("email") String email
    );

    @POST("api/customers")
    Call<Customer> createCustomer(@Body Customer customer);

    @PUT("api/customers/{id}")
    Call<Customer> updateCustomer(@Path("id") int id, @Body Customer customer);

    @PUT("api/customers/lastmessagedate/{id}")
    Call<Customer> updateLastMessageDate(@Path("id") int id, @Body String lastMessageDate);
    
    @PUT("api/customers/updateProfile/{id}")
    Call<Customer> updateProfile(@Path("id") int id, @Body Customer customer);
    @PUT("api/customers/changePassword")
    Call<Customer> changePassword(@Query("email") String email, @Query("oldPass") String oldPass, @Query("newPass") String newPass);

}

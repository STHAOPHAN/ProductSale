package com.example.product_sale.service;

import com.example.product_sale.config.AppConfig;
import com.example.product_sale.models.Pet;

import java.util.List;
import java.util.Map;

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
import retrofit2.http.QueryMap;

public interface PetApiService {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(AppConfig.DATABASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    @GET("api/pets")
    Call<List<Pet>> getPets(@Query("petType") String petType, @Query("breed") String breed);

    @POST("api/pets")
    Call<Pet> createPet(@Body Pet pet);

    @PUT("api/pets/{id}")
    Call<Pet> updatePet(@Path("id") int id, @Body Pet pet);

    @DELETE("api/pets/{id}")
    Call<Void> deletePet(@Path("id") int id);

    @GET("api/pets")
    Call<List<Pet>> getPetsByIds(@QueryMap Map<String, Object> options);
}

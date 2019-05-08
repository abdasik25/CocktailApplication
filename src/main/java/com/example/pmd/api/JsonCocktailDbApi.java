package com.example.pmd.api;

import com.example.pmd.data.Drinks;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonCocktailDbApi {
    @GET("filter.php")
    Call<Drinks> getCocktailIDByAlcoholDrink(@Query("i") String drink);
    @GET("lookup.php")
    Call<Drinks> getCocktailInfoByID(@Query("i") int id);
}

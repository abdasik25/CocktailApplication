package com.example.pmd.service;

import com.example.pmd.api.JsonCocktailDbApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkCocktailService {

    private static NetworkCocktailService networkService = null;
    private static final String BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/";
    private Retrofit retrofit;

    private NetworkCocktailService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkCocktailService getInstance() {
        return networkService == null ? new NetworkCocktailService() : networkService;
    }

    public JsonCocktailDbApi getJsonApi() {
        return retrofit.create(JsonCocktailDbApi.class);
    }

}
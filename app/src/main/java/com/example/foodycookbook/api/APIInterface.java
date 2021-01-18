package com.example.foodycookbook.api;

import com.example.foodycookbook.model.MealData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("random.php")
    Call<MealData> getRandomMeals();

    @GET("search.php?")
    Call<MealData> searchMeal(@Query("s") String name);
}

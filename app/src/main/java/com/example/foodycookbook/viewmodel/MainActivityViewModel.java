package com.example.foodycookbook.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.foodycookbook.api.APIClient;
import com.example.foodycookbook.api.APIInterface;
import com.example.foodycookbook.model.MealData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends AndroidViewModel {
    APIInterface apiInterface;
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        apiInterface= APIClient.getClient().create(APIInterface.class);
    }


   public     MutableLiveData<MealData> mealList=new MutableLiveData<MealData>();


    // random api
    public void getRandomMeals() {
        Call call = apiInterface.getRandomMeals();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                MealData resource= (MealData) response.body();
                mealList.postValue(resource);
            }
            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });

    }

    // search api
    public void searchForMeal(String searchS) {

        Call call=apiInterface.searchMeal(searchS);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                MealData resource= (MealData) response.body();
                mealList.postValue(resource);
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
}

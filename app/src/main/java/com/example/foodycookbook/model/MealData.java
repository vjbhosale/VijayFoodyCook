package com.example.foodycookbook.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MealData {

    @SerializedName("meals")
    public ArrayList<Datum> meals = null;

    public class Datum{
        @SerializedName("idMeal")
        public String idMeal="";
        @SerializedName("strMeal")
        public String strMeal="";
        @SerializedName("strCategory")
        public String strCategory="";
        @SerializedName("strArea")
        public String strArea="";
        @SerializedName("strInstructions")
        public String strInstructions="";
        @SerializedName("strMealThumb")
        public String strMealThumb="";
        @SerializedName("strTags")
        public String strTags="";
        @SerializedName("strYoutube")
        public String strYoutube="";



    }





}

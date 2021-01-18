package com.example.foodycookbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodycookbook.adapter.MealAdapter;
import com.example.foodycookbook.api.APIClient;
import com.example.foodycookbook.api.APIInterface;
import com.example.foodycookbook.model.MealData;
import com.example.foodycookbook.viewmodel.MainActivityViewModel;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView foodRV;
    EditText searchET;
    MainActivityViewModel mainActivityViewModel;
    MealAdapter mealAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        foodRV=(RecyclerView)findViewById(R.id.foodRV);
        searchET=(EditText)findViewById(R.id.searchET);

        mainActivityViewModel=new ViewModelProvider(this).get(MainActivityViewModel.class);

        mealAdapter= new MealAdapter(this,null);
        foodRV.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        foodRV.setAdapter(mealAdapter);




        if (isInternetOn())
        {
            // random api call
            mainActivityViewModel.getRandomMeals();

        }


        mainActivityViewModel.mealList.observe(this, new Observer<MealData>() {
            @Override
            public void onChanged(MealData mealData) {
                // adding data into list
                mealAdapter.addData(mealData.meals);

            }
        });


        searchET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {


                if (!editable.toString().trim().isEmpty() )
                {
                    if (isInternetOn()) {
                        // search api
                        mainActivityViewModel.searchForMeal(editable.toString());
                    }
                }else {

                    if (mainActivityViewModel.mealList.getValue().meals!=null){
                        mainActivityViewModel.mealList.getValue().meals.clear();
                    }


                    
                    mealAdapter.addData(null);
                }



            }
        });




    }




    // internet check
    public boolean isInternetOn() {

        // get Connectivity Manager object to check connection
        ConnectivityManager connec =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        // Check for network connections
        if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {

            return true;

        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {

            return false;
        }
        return false;
    }

}
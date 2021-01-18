package com.example.foodycookbook.adapter;

import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.foodycookbook.MainActivity;
import com.example.foodycookbook.R;
import com.example.foodycookbook.model.MealData;
import java.util.ArrayList;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.ViewHolder> {
    ArrayList<MealData.Datum> mealsList;
    MainActivity mainActivity;
    ArrayList<String> selectedList=new ArrayList<>();

    public MealAdapter(MainActivity mainActivity, ArrayList<MealData.Datum> mealsList) {
        this.mealsList=mealsList;
        this.mainActivity=mainActivity;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View mealItem=layoutInflater.inflate(R.layout.meal_singlerow,parent,false);
        ViewHolder viewHolder=new ViewHolder(mealItem);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        // selection logic using idMeal parameter

        if (selectedList.size()==0)
        {
            holder.bookmarkIMG.setImageDrawable(mainActivity.getDrawable(R.drawable.ic_bookmark_unselect));
        }else {
            int isSelected =-1;

            for (int i=0;i<selectedList.size();i++)
            {
                if (mealsList.get(position).idMeal.equals(selectedList.get(i)))
                {
                    isSelected=i;
                    break;
                }
            }

            if (isSelected==-1)
            {
                holder.bookmarkIMG.setImageDrawable(mainActivity.getDrawable(R.drawable.ic_bookmark_unselect));
            }else {
                holder.bookmarkIMG.setImageDrawable(mainActivity.getDrawable(R.drawable.ic_bookmark_selected));
            }
        }







        // display data
       Glide.with(mainActivity).load(mealsList.get(position).strMealThumb).into(holder.mealIMG);
       holder.meal_titleTV.setText(mealsList.get(position).strMeal);


       holder.bookmarkIMG.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               // selection logic using idMeal parameter
               if (selectedList.size()==0)
               {
                   selectedList.add(mealsList.get(position).idMeal);
               }else {

                   int isSelected =-1;

                   for (int i=0;i<selectedList.size();i++)
                   {
                       if (mealsList.get(position).idMeal.equals(selectedList.get(i)))
                       {
                           isSelected=i;
                           break;
                       }
                   }

                   if (isSelected==-1)
                   {
                       selectedList.add(mealsList.get(position).idMeal);
                   }else {
                       selectedList.remove(isSelected);
                   }

               }

               notifyDataSetChanged();

           }
       });
    }



    @Override
    public int getItemCount() {
        if (mealsList!=null)
        {
            return mealsList.size();
        }else {
            return 0;
        }

    }

    public void addData(ArrayList<MealData.Datum> mealsList) {
        /*if (mealsList!=null)
        {
            this.mealsList=mealsList;
        }else {
            this.mealsList.clear();
        }*/

        this.mealsList=mealsList;

        notifyDataSetChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public AppCompatImageView mealIMG,bookmarkIMG;
        public TextView meal_titleTV;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

           mealIMG=(AppCompatImageView)itemView.findViewById(R.id.mealIMG);
            bookmarkIMG=(AppCompatImageView)itemView.findViewById(R.id.bookmarkIMG);
            meal_titleTV=(TextView)itemView.findViewById(R.id.meal_titleTV);
        }
    }




}

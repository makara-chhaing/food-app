package com.example.foodapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodapp.Databasehelper.Database;
import com.example.foodapp.Entity.Food;
import com.example.foodapp.R;
import com.example.foodapp.Util.Util;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodAdapterViewHolder> {

    Context context;
    Database database = Util.fooduser_db;
    List<Food> foodList;

    public FoodAdapter(Context context, Database database){
        this.context = context;
    }
    @NonNull
    @Override
    public FoodAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.food_component, parent,false);
        return new FoodAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapterViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class FoodAdapterViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView header, body;
        public FoodAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.componet_iv_img);
            header = itemView.findViewById(R.id.componet_tv_header);
            body = itemView.findViewById(R.id.componet_tv_body);
        }
    }
}

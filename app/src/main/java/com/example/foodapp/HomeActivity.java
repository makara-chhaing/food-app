package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodapp.Adapter.FoodAdapter;

public class HomeActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        recyclerView = findViewById(R.id.recyclerView);

//        FoodAdapter adapter = new FoodAdapter(this);
//        recyclerView.setAdapter();
    }
}
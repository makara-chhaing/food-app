package com.example.foodapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodapp.Databasehelper.Database;
import com.example.foodapp.Entity.Food;
import com.example.foodapp.Util.Util;

import java.util.List;

public class HomeActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView id1, name1, des1;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if(Util.fooduser_db == null){
            Log.d(Util.DEBUG, "Create Database");
            Util.fooduser_db = new Database(this,null);
        }
        Intent intent = getIntent();
        sharedPreferences = getSharedPreferences(Util.LOGIN_STATE, MODE_PRIVATE);
        int loginState = sharedPreferences.getInt(Util.USER_ID, 0);
        if(intent==null || loginState == 0){
            startActivity(new Intent(this, LoginActivity.class));
        }



        if(intent != null){
            long userID = intent.getLongExtra(Util.USER_ID, 0);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(Util.USER_ID, (int) userID);
            editor.apply();
            Food cake = new Food("Cake");
            cake.setUserID((int)userID);
            cake.setDescription("yummmmmm");
            cake.setImgID(1);
            Log.d(Util.DEBUG, userID + " <- userID for food");
            long fooduserId = Util.fooduser_db.addFood(cake);
            Log.d(Util.DEBUG, fooduserId + " <- userID for food");

            List<Food> foodList = Util.fooduser_db.fetchFood((int)userID);
            Log.d(Util.DEBUG, "food size: " + foodList.size());
            if(foodList.size() > 0){
                Food food = foodList.get(0);
                name1 = findViewById(R.id.foodname);
                name1.setText(food.getName());
                id1 = findViewById(R.id.foodid);
                id1.setText(food.getFoodId() + "");
                des1 = findViewById(R.id.fooddescription);
                des1.setText(food.getDescription());
            }
        }


        recyclerView = findViewById(R.id.recyclerView);






//        FoodAdapter adapter = new FoodAdapter(this);
//        recyclerView.setAdapter();
    }
}
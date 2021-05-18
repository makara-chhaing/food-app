package com.example.foodapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
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
//        if(Util.fooduser_db == null){
//            Log.d(Util.DEBUG, "Create Database");
//            Util.fooduser_db = new Database(this,null);
//        }
//        long userID = 0;
//        int loginState=0;
//        Intent intent = getIntent();
//
//        if(intent != null){
//            userID = intent.getLongExtra(Util.USER_ID, 0);
//            sharedPreferences = getSharedPreferences(Util.LOGIN_STATE, MODE_PRIVATE);
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putInt(Util.USER_ID, (int) userID);
//            editor.putInt(Util.LOGIN_STATE, (int) userID);
//            Log.d(Util.DEBUG, "loginstate uid: " + userID);
//            editor.commit();
//        }else {
//            sharedPreferences = getSharedPreferences(Util.LOGIN_STATE, MODE_PRIVATE);
//            userID = sharedPreferences.getInt(Util.USER_ID, 0);
//            loginState = sharedPreferences.getInt(Util.LOGIN_STATE, 0);
//        }
//        Toast.makeText(this, "logged in!", Toast.LENGTH_LONG).show();
//        Log.d(Util.DEBUG, "loginstate: " + loginState);
//
//
//        if( loginState > 0){
//            Food cake = new Food("Cake");
//            cake.setUserID((int)loginState);
//            cake.setDescription("yummmmmm");
//            Log.d(Util.DEBUG, loginState + " <- userID for food");
//            long fooduserId = Util.fooduser_db.addFood(cake);
//            Log.d(Util.DEBUG, fooduserId + " <- userID for food");
//
////            List<Food> foodList = Util.fooduser_db.fetchFood(loginState);
////            Log.d(Util.DEBUG, "food size: " + foodList.size());
////            if(foodList.size() > 0){
////                Food food = foodList.get(0);
////                name1 = findViewById(R.id.foodname);
////                name1.setText(food.getName());
////                id1 = findViewById(R.id.foodid);
////                id1.setText(food.getFoodId() + "");
////                des1 = findViewById(R.id.fooddescription);
////                des1.setText(food.getDescription());
////            }
//
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putInt(Util.USER_ID, (int) userID);
//            editor.putInt(Util.LOGIN_STATE, loginState);
//            editor.commit();
//        }
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        if(loginState > 0){
//            editor.putInt(Util.LOGIN_STATE, loginState);
//        }if (userID > 0){
//            editor.putInt(Util.USER_ID, (int) userID);
//        }
//
//        editor.commit();
//
//
//        recyclerView = findViewById(R.id.recyclerView);
//
//




//        FoodAdapter adapter = new FoodAdapter(this);
//        recyclerView.setAdapter();
    }

    public void addNewFood(View v){
        startActivity(new Intent(this, NewFoodActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.foodmenu, menu);
        return true;

    }
}
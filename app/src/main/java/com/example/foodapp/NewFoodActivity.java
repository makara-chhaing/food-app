package com.example.foodapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.foodapp.Entity.Food;
import com.example.foodapp.Util.Util;

import java.io.IOException;

public class NewFoodActivity extends AppCompatActivity {
ImageView imageView;
TextView imageText, title, description, location, time;
Uri imageUri;
Bitmap imageBitmap;
int userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_food);
        imageView = findViewById(R.id.iv_addNewFood);
        imageText = findViewById(R.id.tv_add_image_id);
        title = findViewById(R.id.et_title_id);
        description = findViewById(R.id.et_description_id);
        location = findViewById(R.id.et_location);
        time = findViewById(R.id.et_pickup);
        userId = getIntent().getIntExtra(Util.USER_ID,0);

    }

    public void addImage(View v){
        try {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, 1);

        }catch (Exception e){
            Log.e(Util.DEBUG, "Error Adding Image!");
        }
    }

    public void addFood(View v){
        try {
            if(imageBitmap!=null){
                Food food = new Food(title.getText().toString());
                food.setImgBitmap(imageBitmap);
                food.setDescription(description.getText().toString());
                food.setOwnerID(userId);
                food.setUserID(userId);
                Util.fooduser_db.addFood(food);
                startActivity(new Intent(this, HomeActivity.class));
            }
        } catch (Exception e) {
            Log.d("result: ",  " :Error addfoof?");
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data != null && data.getData()!=null){
            imageUri=data.getData();
            try {
                imageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imageView.setImageBitmap(imageBitmap);
                imageText.setText("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
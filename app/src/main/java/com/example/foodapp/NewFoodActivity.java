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
TextView imageText;
Uri imageUri;
Bitmap imageBitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_food);
        imageView = findViewById(R.id.iv_addNewFood);
        imageText = findViewById(R.id.tv_add_image_id);
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
                Food creeper = new Food("creeper");
                creeper.setImgBitmap(imageBitmap);
                Util.fooduser_db.addFood(creeper);
                Toast.makeText(this, "add food succeed", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
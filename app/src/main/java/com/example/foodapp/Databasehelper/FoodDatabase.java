package com.example.foodapp.Databasehelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.example.foodapp.Entity.Food;
import com.example.foodapp.Util.Util;

public class FoodDatabase extends SQLiteOpenHelper {
    public FoodDatabase(@Nullable Context context, @Nullable SQLiteDatabase.CursorFactory factory) {
        super(context, Util.DATABASE_NAME, factory, Util.DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + Util.FOOD_TABLE_NAME + " (" + Util.FOOD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Util.FOOD_NAME + " TEXT, " + Util.FOOD_DESCRIPTION + " TEXT)";
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = "DROP TABLE IF EXISTS " + Util.FOOD_TABLE_NAME;
        db.execSQL(DROP_TABLE);

        onCreate(db);
    }

    public long addFood(Food food){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.FOOD_NAME, food.getName());
        contentValues.put(Util.FOOD_IMAGE_ID, food.getImgID());
        contentValues.put(Util.FOOD_DESCRIPTION, food.getDescription());
        long newRow = db.insert(Util.USER_TABLE_NAME, null, contentValues);
        db.close();
        return newRow;
    }
}

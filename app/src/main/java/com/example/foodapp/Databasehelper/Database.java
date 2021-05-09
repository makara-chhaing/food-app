package com.example.foodapp.Databasehelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;
import com.example.foodapp.Entity.Food;
import com.example.foodapp.Entity.User;
import com.example.foodapp.Util.Util;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable SQLiteDatabase.CursorFactory factory) {
        super(context, Util.DATABASE_NAME, factory, Util.DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + Util.USER_TABLE_NAME + " (" + Util.USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Util.USERNAME + " TEXT, " + Util.NAME + " TEXT," + Util.ADDRESS + " TEXT," + Util.EMAIL + " TEXT,"
                + Util.PHONE + " INTEGER," +  Util.PASSWORD + " TEXT)";
        String CREATE_FOOD_TABLE = "CREATE TABLE " + Util.FOOD_TABLE_NAME + " (" + Util.FOOD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Util.FOOD_NAME + " TEXT, " + Util.FOOD_DESCRIPTION + " TEXT)";
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_FOOD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = "DROP TABLE IF EXISTS " + Util.USER_TABLE_NAME;
        db.execSQL(DROP_TABLE);
        DROP_TABLE = "DROP TABLE IF EXISTS " + Util.FOOD_TABLE_NAME;
        db.execSQL(DROP_TABLE);


        onCreate(db);
    }

    public long addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.USERNAME, user.getUsername());
        contentValues.put(Util.PASSWORD, user.getPassword());
        if (!user.getName().equals("")) contentValues.put(Util.NAME, user.getName());
        if (!user.getPhone().equals(""))contentValues.put(Util.PHONE, user.getPhone());
        if (!user.getEmail().equals(""))contentValues.put(Util.EMAIL, user.getEmail());
        if (!user.getAddress().equals(""))contentValues.put(Util.ADDRESS, user.getAddress());
        long newRow = db.insert(Util.USER_TABLE_NAME, null, contentValues);
        db.close();
        return newRow;
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

    public long fetchUser(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Util.USER_TABLE_NAME,new String[]{Util.USER_ID}, Util.USERNAME + " =? AND " + Util.PASSWORD + " =? ",
                new String[] {username, password}, null, null, null);
        long userId = 0;
        if (cursor != null && cursor.moveToFirst()) {
            userId = cursor.getLong(0);
            return userId;
        }

        Log.d(Util.DEBUG, "rowNum: " + userId);
        db.close();
        return 0;
    }
}

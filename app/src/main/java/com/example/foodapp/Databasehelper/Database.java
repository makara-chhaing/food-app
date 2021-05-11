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

import java.util.ArrayList;
import java.util.List;

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
                + Util.FOOD_NAME + " TEXT, " + Util.FOOD_IMAGE_ID + " INTEGER," + Util.FOOD_DESCRIPTION + " TEXT, " + Util.USER_ID + " INTEGER)";
        String CREATE_USER_FOOD_TABLE = "CREATE TABLE " + Util.USER_FOOD_TABLE_NAME + " (" + Util.FOOD_ID + " INTEGER PRIMARY KEY, "
                + Util.USER_ID + " INTEGER )";
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_FOOD_TABLE);
//        db.execSQL(CREATE_USER_FOOD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = "DROP TABLE IF EXISTS " + Util.USER_FOOD_TABLE_NAME;
        db.execSQL(DROP_TABLE);
        DROP_TABLE = "DROP TABLE IF EXISTS " + Util.USER_TABLE_NAME;
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
        ContentValues foodContentValues = new ContentValues();
        foodContentValues.put(Util.FOOD_NAME, food.getName());
        foodContentValues.put(Util.FOOD_IMAGE_ID, food.getImgID());
        foodContentValues.put(Util.FOOD_DESCRIPTION, food.getDescription());
        foodContentValues.put(Util.USER_ID, food.getUserID());
        long newRow = db.insert(Util.FOOD_TABLE_NAME, null, foodContentValues);
        db.close();

//        SQLiteDatabase dbfooduser = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(Util.USER_ID, food.getUserID());
//        contentValues.put(Util.FOOD_ID, newRow);
//        long nr = dbfooduser.insert(Util.USER_FOOD_TABLE_NAME, null, contentValues);
//        dbfooduser.close();

        Log.d(Util.DEBUG, "add food: " + newRow);
//        Log.d(Util.DEBUG, "add food user: " + nr);
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
        assert cursor != null;
        cursor.close();
        db.close();
        return 0;
    }

    public List<Food> fetchFood(int userID){
        Log.d(Util.DEBUG, "here #1 ");
        List<Food> list = new ArrayList<>();
        Log.d(Util.DEBUG, "here #2 ");
        SQLiteDatabase db = this.getReadableDatabase();
        Log.d(Util.DEBUG, "here #3 ");
//        String query = "SELECT " + Util.FOOD_ID + "," + Util.FOOD_NAME + "," + Util.FOOD_IMAGE_ID + "," + Util.FOOD_DESCRIPTION + " FROM " + Util.FOOD_TABLE_NAME +
//                " LEFT JOIN " +  Util.USER_FOOD_TABLE_NAME + " ON " + Util.USER_FOOD_TABLE_NAME + "." + Util.USER_ID + "=" +
//                Util.USER_TABLE_NAME + "." + Util.USER_ID + " WHERE " + Util.USER_TABLE_NAME + "." + Util.USER_ID + "=" + userID;

        String query = "SELECT " + Util.FOOD_ID + "," + Util.FOOD_NAME + "," + Util.FOOD_IMAGE_ID + "," + Util.FOOD_DESCRIPTION + " FROM " + Util.FOOD_TABLE_NAME +
                " JOIN " + Util.USER_TABLE_NAME + " ON " + Util.FOOD_TABLE_NAME + "." + Util.USER_ID + "=" +
                Util.USER_TABLE_NAME + "." + Util.USER_ID + " WHERE " + Util.USER_TABLE_NAME + "." +Util.USER_ID + "=" + userID;
        Log.d(Util.DEBUG, "here #4 ");
        Cursor c = db.rawQuery(query, null);
        Log.d(Util.DEBUG, "here #5 ");
//        Cursor c = db.query(
//                Util.FOOD_TABLE_NAME,
//                new String[]{Util.FOOD_ID, Util.FOOD_NAME,Util.FOOD_IMAGE_ID,Util.FOOD_DESCRIPTION},
//                Util.USER_ID + "=?",
//                new String[]{userID+""},
//                null,
//                null,
//                Util.FOOD_ID + " ASC"
//        );
        if(c.moveToNext()){
            Log.d(Util.DEBUG, "here #6 ");
            do{
                int foodID = c.getInt(0);
                String name = c.getString(1);
                int foodImgID = c.getInt(2);
                String des = c.getString(3);
                Log.d(Util.DEBUG, "here #7 ");
                if(foodID!=0 && name != null){
                    Log.d(Util.DEBUG, "here #8 ");
                    Food food = new Food(name);
                    Log.d(Util.DEBUG, "here #9 ");
                    food.setUserID(userID);
                    Log.d(Util.DEBUG, "here #10 ");
                    if(des != null){
                        food.setDescription(des);
                        Log.d(Util.DEBUG, "here #11 ");
                    }
                    if(foodImgID != 0){
                        food.setImgID(foodImgID);
                        Log.d(Util.DEBUG, "here #12 ");
                    }
                    list.add(food);
                    Log.d(Util.DEBUG, "here #13 ");
                }
            }while (c.moveToNext());
            Log.d(Util.DEBUG, "here #14 ");
        }
        Log.d(Util.DEBUG, "here #15 ");
        c.close();
        Log.d(Util.DEBUG, "here #16 ");
        db.close();
        Log.d(Util.DEBUG, "here #17 done ");
        return list;
    }
}

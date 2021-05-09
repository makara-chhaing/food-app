package com.example.foodapp.Util;

import com.example.foodapp.Databasehelper.Database;

public class Util {
    public  static Database user_db;
    public static final String DEBUG = "databaseCreate1";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "fooduser_db";

    public static final String USER_TABLE_NAME = "users_table";
    public static final String USER_ID = "user_id";
    public static final String USERNAME = "user_name";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";
    public static final String ADDRESS = "address";
    public static final String NAME = "name";

    public static final String FAVOURITE_FOOD_TABLE_NAME = "favourite_food_table";

    public static final String FOOD_TABLE_NAME = "food_table";
    public static final String FOOD_ID = "food_id";
    public static final String FOOD_IMAGE_ID = "food_img_id";
    public static final String FOOD_NAME = "food_name";
    public static final String FOOD_DESCRIPTION = "food_description";

}

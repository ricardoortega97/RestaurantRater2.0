package com.example.restaurantrater20.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class RestaurantDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "restaurant.db";
    private static final int DATABASE_VERSION = 1;

    public RestaurantDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String CREATE_TABLE_RESTAURANT = "CREATE TABLE restaurant (" +
            "restaurant_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT, " +
            "streetAddress TEXT, " +
            "city TEXT, " +
            "state TEXT, " +
            "zipcode TEXT)";

    private static final String CREATE_TABLE_DISH = "CREATE TABLE dish (" +
            "dish_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "restaurant_id INTEGER, " +
            "name TEXT, " +
            "type TEXT, " +
            "rating REAL, " +
            "FOREIGN KEY (restaurant_id) REFERENCES restaurant(restaurant_id))";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_RESTAURANT);
        db.execSQL(CREATE_TABLE_DISH);
        db.execSQL("PRAGMA foreign_keys=ON;");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.w(RestaurantDBHelper.class.getName(),
                "Upgrading database from version " + i + " to " + i1 +
                        ", which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS dish");
        db.execSQL("DROP TABLE IF EXISTS restaurant");
        onCreate(db);

    }
}

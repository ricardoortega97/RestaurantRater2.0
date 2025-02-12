package com.example.restaurantrater20.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.restaurantrater20.model.Dish;
import com.example.restaurantrater20.model.Restaurant;

public class RestaurantDataSource {

    private SQLiteDatabase database;
    private RestaurantDBHelper restaurantDBHelper;

    public RestaurantDataSource(Context context) {
        restaurantDBHelper = new RestaurantDBHelper(context);
    }

    public void open() {
        database = restaurantDBHelper.getWritableDatabase();
    }
    public void close() {
        restaurantDBHelper.close();
    }
    //Restaurant DataSource
    public boolean insertRestaurant(Restaurant r) {
        boolean didSucceed = false;
        try {
            ContentValues initialValues = new ContentValues();

            initialValues.put("name", r.getName());
            initialValues.put("streetAddress", r.getStreetAddress());
            initialValues.put("city", r.getCity());
            initialValues.put("state", r.getState());
            initialValues.put("zipcode", r.getZipcode());

            didSucceed = database.insert("restaurant", null, initialValues) > 0;
        } catch (Exception e) {
            // Do nothing - will return false if there is an exception
        }
        Log.d("RestaurantDataSource", "insertRestaurant: " + didSucceed);
        return didSucceed;
    }
    public boolean updateRestaurant(Restaurant r) {
        boolean didSucceed = false;
        try {
            long rowId = (long) r.getRestaurant_id();
            ContentValues updateValues = new ContentValues();

            updateValues.put("name", r.getName());
            updateValues.put("streetAddress", r.getStreetAddress());
            updateValues.put("city", r.getCity());
            updateValues.put("state", r.getState());
            updateValues.put("zipcode", r.getZipcode());

            didSucceed = database.update("restaurant", updateValues, "_id=" + rowId, null) > 0;
        } catch (Exception e) {
            // Do nothing - will return false if there is an exception
        }
        return didSucceed;
    }
    public int getLastRestaurantId() {
        int lastId;
        try {
            String query = "Select MAX(restaurant_id) from restaurant";
            Cursor cursor = database.rawQuery(query, null);

            cursor.moveToFirst();
            lastId = cursor.getInt(0);
            cursor.close();
        } catch (Exception e) {
            lastId = -1;
        }
        return lastId;
    }

    // Dish DataSource
    public boolean insertDish(Dish d) {
        boolean didSucceed = false;
        try {
            ContentValues initialValues = new ContentValues();

            initialValues.put("restaurant_id", d.getRestaurant_id());
            initialValues.put("name", d.getName());
            initialValues.put("type", d.getType());
            initialValues.put("rating", d.getRating());

            didSucceed = database.insert("dish", null, initialValues) > 0;
        } catch (Exception e) {
            // Do nothing - will return false if there is an exception
        }
        Log.d("RestaurantDataSource", "insertDish: " + didSucceed);
        return didSucceed;
    }
    public boolean updateDish(Dish d) {
        boolean didSucceed = false;
        try {
            long rowId = (long) d.getDish_id();
            ContentValues updateValues = new ContentValues();

            updateValues.put("restaurant_id", d.getRestaurant_id());
            updateValues.put("name", d.getName());
            updateValues.put("type", d.getType());
            updateValues.put("rating", d.getRating());

            didSucceed = database.update("dish", updateValues, "_id=" + rowId, null) > 0;
        } catch (Exception e) {
            // Do nothing - will return false if there is an exception
        }
        return didSucceed;
    }
    public int getLastDishId() {
        int lastId;
        try {
            String query = "Select MAX(dish_id) from dish";
            Cursor cursor = database.rawQuery(query, null);

            cursor.moveToFirst();
            lastId = cursor.getInt(0);
            cursor.close();
        } catch (Exception e) {
            lastId = -1;
        }
        return lastId;
    }
}
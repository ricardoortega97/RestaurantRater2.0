package com.example.restaurantrater20.model;

import android.media.Rating;

public class Dish {

    private int dish_id;
    private int restaurant_id;
    private String name;
    private String type;
    private float rating;

    public Dish() {
        dish_id = -1;
    }

    public int getDish_id() {
        return dish_id;
    }

    public void setDish_id(int d) {
        dish_id = d;
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        name = n;
    }

    public String getType() {
        return type;
    }

    public void setType(String t) {
        type = t;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float r) {
        rating = r;
    }
    public int getRestaurant_id() {
        return restaurant_id;
    }
    public void setRestaurant_id(int r) {
        restaurant_id = r;
    }
}

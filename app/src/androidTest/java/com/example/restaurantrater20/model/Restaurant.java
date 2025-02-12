package com.example.restaurantrater20.model;

public class Restaurant {

    private int restaurant_id;
    private String name;
    private String streetAddress;
    private String city;
    private String state;
    private String zipcode;

    public Restaurant() {
        restaurant_id = -1;
    }
    public int getRestaurant_id() {
        return restaurant_id;
    }
    public void setRestaurant_id(int i) {
        this.restaurant_id = i;
    }
    public String getStreetAddress() {
        return streetAddress;
    }
    public void setStreetAddress(String s) {
        this.streetAddress = s;
    }
    public String getName() {
        return name;
    }
    public void setName(String n) {
        name = n;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String c) {
        city = c;
    }
    public String getState() {
        return state;
    }
    public void setState(String s) {
        state = s;
    }
    public String getZipcode() {
        return zipcode;
    }
    public void setZipcode(String z) {
        zipcode = z;
    }
}

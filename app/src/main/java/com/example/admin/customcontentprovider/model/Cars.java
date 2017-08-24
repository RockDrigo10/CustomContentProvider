package com.example.admin.customcontentprovider.model;

/**
 * Created by Admin on 8/23/2017.
 */

public class Cars {
    String model;
    String brand;
    int year;
    String color;
    String description;

    public Cars() {
    }

    public Cars(String model, String brand, int year, String color, String description) {
        this.model = model;
        this.brand = brand;
        this.year = year;
        this.color = color;
        this.description = description;
    }



    public void setModel(String model) {
        this.model = model;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }

    public int getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }

    public String getDescription() {
        return description;
    }
}

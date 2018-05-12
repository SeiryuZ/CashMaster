package com.halim.adam.cashmaster.Objects;

/**
 * Created by Adam on 27-Apr-18.
 */

public class Jar {
    private int id;
    private String name;
    private float price;

    public Jar(){}

    public Jar(int id, String name, float price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}

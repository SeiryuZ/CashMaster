package com.halim.adam.cashmaster.Objects;

import java.util.Date;

/**
 * Created by Adam on 27-Apr-18.
 */

public class Income {
    private int id;
    private String name;
    private float price;
    private Date date;

    public Income(){}

    public Income(int id, String name, float price, Date date){
        this.id = id;
        this.name = name;
        this.price = price;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

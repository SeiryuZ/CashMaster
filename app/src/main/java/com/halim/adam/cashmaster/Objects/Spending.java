package com.halim.adam.cashmaster.Objects;

import java.util.Date;

/**
 * Created by Adam on 27-Apr-18.
 */

public class Spending {
    private int id;
    private String name;
    private Date date;
    private float price;
    private int categoryId;
    private int jarId;

    public Spending(){}

    public Spending(int id, String name, Date date, float price, int categoryId, int jarId) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.price = price;
        this.categoryId = categoryId;
        this.jarId = jarId;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getJarId() {
        return jarId;
    }

    public void setJarId(int jarId) {
        this.jarId = jarId;
    }
}

package com.halim.adam.cashmaster.Objects;

import com.halim.adam.cashmaster.DatabaseHelper;

import java.util.Date;

/**
 * Created by Adam on 27-Apr-18.
 */

public class Income {
    private int id;
    private String name;
    private float amount;
    private Date date;

    public Income(){}

    public Income(int id, String name, float amount, Date date){
        this.id = id;
        this.name = name;
        this.amount = amount;
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

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

package com.halim.adam.cashmaster.Objects;

import android.database.sqlite.SQLiteDatabase;

import com.halim.adam.cashmaster.DatabaseHelper;

/**
 * Created by Adam on 27-Apr-18.
 */

public class Category {
    private int id;
    private String name;

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
}

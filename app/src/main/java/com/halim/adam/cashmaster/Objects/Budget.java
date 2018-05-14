package com.halim.adam.cashmaster.Objects;

import java.util.ArrayList;

public class Budget {
    private int id;
    private String name;
    private float portion;

    public Budget(){}

    public Budget(int id, String name, float portion){
        this.id = id;
        this.name = name;
        this.portion = portion;
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

    public float getPortion() {
        return portion;
    }

    public void setPortion(float portion) {
        this.portion = portion;
    }
}

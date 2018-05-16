package com.halim.adam.cashmaster.Objects;

public class Budget {
    private int id;
    private String name;
    private float portion;
    private float amount;

    public Budget(){}

    public Budget(int id, String name, float portion, float amount){
        this.id = id;
        this.name = name;
        this.portion = portion;
        this.amount = amount;
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

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}

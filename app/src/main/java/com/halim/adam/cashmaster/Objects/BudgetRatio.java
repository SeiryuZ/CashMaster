package com.halim.adam.cashmaster.Objects;

public class BudgetRatio {
    private int id;
    private String name;
    private float ratio;

    public BudgetRatio() {
    }

    public BudgetRatio(int id, String name, float ratio) {
        this.id = id;
        this.name = name;
        this.ratio = ratio;
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

    public float getRatio() {
        return ratio;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }
}

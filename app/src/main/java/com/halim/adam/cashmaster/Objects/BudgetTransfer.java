package com.halim.adam.cashmaster.Objects;

public class BudgetTransfer {
    private int id;
    private int jarFromId;
    private int jarToId;
    private float amount;

    public BudgetTransfer() {
    }

    public BudgetTransfer(int id, int jarFromId, int jarToId, float amount) {
        this.id = id;
        this.jarFromId = jarFromId;
        this.jarToId = jarToId;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJarFromId() {
        return jarFromId;
    }

    public void setJarFromId(int jarFromId) {
        this.jarFromId = jarFromId;
    }

    public int getJarToId() {
        return jarToId;
    }

    public void setJarToId(int jarToId) {
        this.jarToId = jarToId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}

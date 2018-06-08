package com.halim.adam.cashmaster.Objects;

public class Budget {
    private int id;
    private int ratioId;
    private int incomeId;
    private float amount;

    public Budget(){}

    public Budget(int id, int ratioId, int incomeId, float amount) {
        this.id = id;
        this.ratioId = ratioId;
        this.incomeId = incomeId;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRatioId() {
        return ratioId;
    }

    public void setRatioId(int ratioId) {
        this.ratioId = ratioId;
    }

    public int getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(int incomeId) {
        this.incomeId = incomeId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}

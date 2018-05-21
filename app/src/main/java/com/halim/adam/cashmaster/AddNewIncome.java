package com.halim.adam.cashmaster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.halim.adam.cashmaster.Objects.BudgetRatio;
import com.halim.adam.cashmaster.Objects.Income;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AddNewIncome extends Activity {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_income);
    }

    public void GetInput(View view) throws ParseException {
        EditText inputName = findViewById(R.id.inputName);
        EditText inputAmount = findViewById(R.id.inputAmount);
        EditText inputDate = findViewById(R.id.inputDate);

        DatabaseHelper dbHelper = new DatabaseHelper(this);

        if (!inputDate.getText().toString().matches("")){
            Date date = DATE_FORMAT.parse(inputDate.getText().toString());
            dbHelper.InsertIncome(inputName.getText().toString(), Float.valueOf(inputAmount.getText().toString()), date);
        } else{
            dbHelper.InsertIncome(inputName.getText().toString(), Float.valueOf(inputAmount.getText().toString()));
        }

        ArrayList<BudgetRatio> budgetRatioList = dbHelper.GetBudgetRatioList();

        if(budgetRatioList == null){
            dbHelper.InsertBudgetRatio("Main", 100);
            budgetRatioList = dbHelper.GetBudgetRatioList();
        }

        // get total ratio
        float ratioTotal = 0;
        for(int c = 0; c < budgetRatioList.size(); c++){
            ratioTotal += budgetRatioList.get(c).getRatio();
        }

        // divide to budgets
        Income latestIncome = dbHelper.GetLatestIncome();
        for(int c = 0; c < budgetRatioList.size(); c++){
            dbHelper.InsertBudget(budgetRatioList.get(c).getId(), latestIncome.getId(), latestIncome.getAmount() * budgetRatioList.get(c).getRatio() / ratioTotal);
        }

        // move to ViewIncome
        Intent intent = new Intent(this, ViewIncomes.class);
        startActivity(intent);
        finish();
    }

    public void MoveToViewIncomeActivity(View view){
        Intent intent = new Intent(this, ViewIncomes.class);
        startActivity(intent);
        finish();
    }

    public void MoveToTransactionHistoryPage(View view) {
        Intent intent = new Intent(this, TransactionHistory.class);
        startActivity(intent);
        finish();
    }
}

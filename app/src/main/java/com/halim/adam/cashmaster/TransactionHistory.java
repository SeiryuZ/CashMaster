package com.halim.adam.cashmaster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.halim.adam.cashmaster.Objects.Spending;

import java.text.ParseException;

public class TransactionHistory extends Activity {
    TextView transactionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);

        DatabaseHelper dbHelper = new DatabaseHelper(this);

        Spending spending = null;
        try {
            spending = dbHelper.GetSpending(1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(spending != null){
            transactionList.append(spending.getName());
        }
    }

    public void MoveToViewCategoryActivity(View view){
        Intent intent = new Intent(this, ViewCategories.class);
        startActivity(intent);
        finish();
    }

    public void MoveToViewIncomeActivity(View view){
        Intent intent = new Intent(this, ViewIncomes.class);
        startActivity(intent);
        finish();
    }

    public void MoveToViewSpendingActivity(View view){
        Intent intent = new Intent(this, ViewSpendings.class);
        startActivity(intent);
        finish();
    }

    public void MoveToViewBudgetActivity(View view){
        Intent intent = new Intent(this, ViewBudgets.class);
        startActivity(intent);
        finish();
    }

    public void MoveToStatisticsPage(View view) {
        Intent intent = new Intent(this, Statistics.class);
        startActivity(intent);
        finish();
    }
}

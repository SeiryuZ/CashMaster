package com.halim.adam.cashmaster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.view.View;
import android.widget.TextView;

import com.halim.adam.cashmaster.Objects.Income;
import com.halim.adam.cashmaster.Objects.Spending;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Statistics extends Activity {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        TextView statisticsText = (TextView) findViewById(R.id.statisticsText);

        ArrayList<Spending> spendingList = new ArrayList<>();
        ArrayList<Income> incomeList = new ArrayList<>();
        float spendingTotalPrice = 0;
        float incomeTotalPrice = 0;

        DatabaseHelper dbHelper = new DatabaseHelper(this);

        try {
            spendingList = dbHelper.GetSpendingFromDate(new Date(), 7);
            incomeList = dbHelper.GetIncomeFromDate(new Date(), 7);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // get last 7 days income & spending
        if(spendingList != null){
            for(int c = 0; c < spendingList.size(); c++){
                spendingTotalPrice += spendingList.get(c).getAmount();
            }
        }
        else{
            spendingTotalPrice = 0;
        }
        if(incomeList != null){
            for(int c = 0; c < incomeList.size(); c++){
                incomeTotalPrice += incomeList.get(c).getAmount();
            }
        }
        else{
            incomeTotalPrice = 0;
        }
        statisticsText.append("Spending for the last 7 days: " + spendingTotalPrice + "\n");
        statisticsText.append("Income for the last 7 days: " + incomeTotalPrice + "\n");

        // get last 30 days of income & spending
        spendingTotalPrice = 0;
        incomeTotalPrice = 0;
        try {
            spendingList = dbHelper.GetSpendingFromDate(new Date(), 30);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            incomeList = dbHelper.GetIncomeFromDate(new Date(), 30);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(spendingList != null){
            for(int c = 0; c < spendingList.size(); c++){
                spendingTotalPrice += spendingList.get(c).getAmount();
            }
        }
        else{
            spendingTotalPrice = 0;
        }
        if(incomeList != null){
            for(int c = 0; c < incomeList.size(); c++){
                incomeTotalPrice += incomeList.get(c).getAmount();
            }
        }
        else{
            incomeTotalPrice = 0;
        }
        statisticsText.append("Spending for the last 30 days: " + spendingTotalPrice + "\n");
        statisticsText.append("Income for the last 30 days: " + incomeTotalPrice + "\n");
    }

    public void MoveToTransactionHistoryPage(View view) {
        Intent intent = new Intent(this, TransactionHistory.class);
        startActivity(intent);
        finish();
    }
}

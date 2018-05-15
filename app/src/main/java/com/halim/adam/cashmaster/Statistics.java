package com.halim.adam.cashmaster;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.halim.adam.cashmaster.Objects.Income;
import com.halim.adam.cashmaster.Objects.Spending;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Statistics extends Activity {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        TextView statisticsText = (TextView) findViewById(R.id.statisticsText);

        ArrayList<Spending> spendingLastWeekList = new ArrayList<>();
        ArrayList<Income> incomeLastWeekList = new ArrayList<>();
        float spendingLastWeekTotalPrice = 0;
        float incomeLastWeekTotalPrice = 0;

        DatabaseHelper dbHelper = new DatabaseHelper(this);

        try {
            spendingLastWeekList = dbHelper.GetSpendingFromDate(new Date(), 7);
            incomeLastWeekList = dbHelper.GetIncomeFromDate(new Date(), 7);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for(int c = 0; c < spendingLastWeekList.size(); c++){
            spendingLastWeekTotalPrice += spendingLastWeekList.get(c).getPrice();
        }
        for(int c = 0; c < incomeLastWeekList.size(); c++){
            incomeLastWeekTotalPrice += incomeLastWeekList.get(c).getPrice();
        }
        statisticsText.append("Spending for the last 7 days: " + spendingLastWeekTotalPrice + "\n");
        statisticsText.append("Income for the last 7 days: " + incomeLastWeekTotalPrice + "\n");

        // print all income
        try {
            incomeLastWeekList = dbHelper.GetIncomeList();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for(int c = 0; c < incomeLastWeekList.size(); c++){
            statisticsText.append(incomeLastWeekList.get(c).getDate() + "\n");
        }
    }
}

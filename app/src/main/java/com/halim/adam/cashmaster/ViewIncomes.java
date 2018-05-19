package com.halim.adam.cashmaster;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.halim.adam.cashmaster.Objects.Income;

import java.text.ParseException;
import java.util.ArrayList;

public class ViewIncomes extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_incomes);

        DatabaseHelper dbHelper = new DatabaseHelper(this);

        TextView incomeText = findViewById(R.id.incomeText);

        ArrayList<Income> incomeList = null;
        try {
            incomeList = dbHelper.GetIncomeList();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(incomeList != null) {
            for (int c = 0; c < incomeList.size(); c++) {
                incomeText.append(incomeList.get(c).getId() + ": " + incomeList.get(c).getName() + " " + incomeList.get(c).getAmount() + " " + incomeList.get(c).getDate() + "\n");
            }
        }else{
            incomeText.append("Empty!");
        }
    }
}

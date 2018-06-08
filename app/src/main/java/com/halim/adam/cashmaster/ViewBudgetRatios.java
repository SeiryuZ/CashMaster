package com.halim.adam.cashmaster;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.halim.adam.cashmaster.Objects.BudgetRatio;

import java.util.ArrayList;

public class ViewBudgetRatios extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_budget_ratios);

        TextView budgetRatiosText = findViewById(R.id.budgetRatiosText);

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        ArrayList<BudgetRatio> budgetRatiosList = dbHelper.GetBudgetRatioList();

        for(int c = 0; c < budgetRatiosList.size(); c++){
            budgetRatiosText.append(budgetRatiosList.get(c).getId() + ": " + budgetRatiosList.get(c).getName() + " " + budgetRatiosList.get(c).getRatio() + "\n");
        }
    }
}

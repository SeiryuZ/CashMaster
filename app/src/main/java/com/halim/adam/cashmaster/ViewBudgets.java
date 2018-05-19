package com.halim.adam.cashmaster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.halim.adam.cashmaster.ListAdapters.BudgetListAdapter;
import com.halim.adam.cashmaster.Objects.Budget;
import com.halim.adam.cashmaster.Objects.BudgetRatio;

import java.util.ArrayList;

public class ViewBudgets extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_budgets);

        DatabaseHelper dbHelper = new DatabaseHelper(this);

        ArrayList<BudgetRatio> budgetRatioList = dbHelper.GetBudgetRatioList();
        ArrayList<Integer> budgetIdList = new ArrayList<>();
        ArrayList<String> budgetNameList = new ArrayList<>();
        ArrayList<Float> ratioList = new ArrayList<>();
        ArrayList<Float> budgetTotalList = new ArrayList<>();

        for(int c = 0; c < budgetRatioList.size(); c++){
            budgetIdList.add(budgetRatioList.get(c).getId());
            budgetNameList.add(budgetRatioList.get(c).getName());
            ratioList.add(budgetRatioList.get(c).getRatio());

            // get budget total
            ArrayList<Budget> budgetList = dbHelper.GetBudgetFromRatio(budgetRatioList.get(c).getId());
            float budgetTotal = 0;
            if(budgetList != null){
                budgetTotal += budgetList.get(c).getAmount();
            }
            budgetTotalList.add(budgetTotal);
        }

        //fill ListView
        ListView listView;

        BudgetListAdapter adapter = new BudgetListAdapter(this, budgetIdList.toArray(new Integer[budgetIdList.size()]),
                budgetNameList.toArray(new String[budgetNameList.size()]), ratioList.toArray(new Float[budgetRatioList.size()]), budgetTotalList.toArray(new Float[budgetTotalList.size()]));
        listView = (ListView) findViewById(R.id.budgetList);
        listView.setAdapter(adapter);
    }

    public void MoveToTransactionHistoryPage(View view) {
        Intent intent = new Intent(this, TransactionHistory.class);
        startActivity(intent);
        finish();
    }
}

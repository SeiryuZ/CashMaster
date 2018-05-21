package com.halim.adam.cashmaster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.halim.adam.cashmaster.ListAdapters.BudgetListAdapter;
import com.halim.adam.cashmaster.Objects.Budget;
import com.halim.adam.cashmaster.Objects.BudgetRatio;
import com.halim.adam.cashmaster.Objects.Spending;

import java.text.ParseException;
import java.util.ArrayList;

public class ViewBudgets extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_budgets);

        DatabaseHelper dbHelper = new DatabaseHelper(this);

        // get data
        ArrayList<BudgetRatio> budgetRatioList = dbHelper.GetBudgetRatioList();
        Integer[] idArray;
        String[] nameArray;
        Float[] ratioArray;
        Float[] totalArray;

        if(budgetRatioList != null){
            idArray = new Integer[budgetRatioList.size()];
            nameArray = new String[budgetRatioList.size()];
            ratioArray = new Float[budgetRatioList.size()];
            totalArray = new Float[budgetRatioList.size()];

            for(int c = 0; c < budgetRatioList.size(); c++){
                idArray[c] = budgetRatioList.get(c).getId();
                nameArray[c] = budgetRatioList.get(c).getName();
                ratioArray[c] = budgetRatioList.get(c).getRatio();

                // get budget total
                ArrayList<Budget> budgetList = dbHelper.GetBudgetFromRatio(budgetRatioList.get(c).getId());
                ArrayList<Spending> spendingList = null;
                try {
                    spendingList = dbHelper.GetSpendingFromRatio(budgetRatioList.get(c).getId());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                float budgetTotal = 0;
                if(budgetList != null){
                    for(int c1 = 0; c1 < budgetList.size(); c1++) {
                        budgetTotal += budgetList.get(c1).getAmount();
                    }
                }
                if(spendingList != null){
                    for(int c1 = 0; c1 < spendingList.size(); c1++) {
                        budgetTotal -= spendingList.get(c1).getAmount();
                    }
                }
                totalArray[c] = budgetTotal;
            }

            final Integer[] ID_ARRAY = idArray;

            //fill ListView
            ListView listView = (ListView) findViewById(R.id.budgetList);

            BudgetListAdapter adapter = new BudgetListAdapter(this, idArray, nameArray, ratioArray, totalArray);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(ViewBudgets.this, EditBudget.class);
                    intent.putExtra("id", ID_ARRAY[position]);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }

    public void MoveToTransactionHistoryPage(View view) {
        Intent intent = new Intent(this, TransactionHistory.class);
        startActivity(intent);
        finish();
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

    public void MoveToAddBudgetRatioActivity(View view){
        Intent intent = new Intent(this, AddNewBudgetRatio.class);
        startActivity(intent);
        finish();
    }
}

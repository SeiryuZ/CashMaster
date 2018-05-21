package com.halim.adam.cashmaster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.halim.adam.cashmaster.ListAdapters.IncomeListAdapter;
import com.halim.adam.cashmaster.Objects.Income;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class ViewIncomes extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_incomes);

        DatabaseHelper dbHelper = new DatabaseHelper(this);

        ArrayList<Income> incomeList = null;
        try {
            incomeList = dbHelper.GetIncomeList();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Integer[] idArray;
        String[] nameArray;
        Float[] amountArray;
        Date[] dateArray;

        if(incomeList != null) {
            idArray = new Integer[incomeList.size()];
            nameArray = new String[incomeList.size()];
            amountArray = new Float[incomeList.size()];
            dateArray = new Date[incomeList.size()];

            for (int c = 0; c < incomeList.size(); c++) {
                idArray[c] = incomeList.get(c).getId();
                nameArray[c] = incomeList.get(c).getName();
                amountArray[c] = incomeList.get(c).getAmount();
                dateArray[c] = incomeList.get(c).getDate();
            }

            // fill list
            ListView incomeListView = (ListView) findViewById(R.id.incomeList);
            IncomeListAdapter incomeListAdapter = new IncomeListAdapter(this, idArray, nameArray, amountArray, dateArray);
            incomeListView.setAdapter(incomeListAdapter);

            // when item clicked
            final Integer[] ID_ARRAY = idArray;
            incomeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(ViewIncomes.this, EditIncome.class);
                    intent.putExtra("id", ID_ARRAY[position]);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }

    public void MoveToViewCategoryActivity(View view){
        Intent intent = new Intent(this, ViewCategories.class);
        startActivity(intent);
        finish();
    }

    public void MoveToAddIncomeActivity(View view){
        Intent intent = new Intent(this, AddNewIncome.class);
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

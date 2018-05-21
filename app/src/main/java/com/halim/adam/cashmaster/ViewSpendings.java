package com.halim.adam.cashmaster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.halim.adam.cashmaster.ListAdapters.SpendingListAdapter;
import com.halim.adam.cashmaster.Objects.Spending;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class ViewSpendings extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_spendings);

        DatabaseHelper dbHelper = new DatabaseHelper(this);

        ArrayList<Spending> spendingList = null;
        try {
            spendingList = dbHelper.GetSpendingList();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Integer[] idArray;
        String[] nameArray;
        Date[] dateArray;
        Float[] amountArray;
        String[] categoryArray;
        String[] ratioArray;

        if(spendingList != null) {
            idArray = new Integer[spendingList.size()];
            nameArray = new String[spendingList.size()];
            dateArray = new Date[spendingList.size()];
            amountArray = new Float[spendingList.size()];
            categoryArray = new String[spendingList.size()];
            ratioArray = new String[spendingList.size()];

            for (int c = 0; c < spendingList.size(); c++) {
                idArray[c] = spendingList.get(c).getId();
                nameArray[c] = spendingList.get(c).getName();
                dateArray[c] = spendingList.get(c).getDate();
                amountArray[c] = spendingList.get(c).getAmount();
                categoryArray[c] = dbHelper.GetCategory(spendingList.get(c).getCategoryId()).getName();
                ratioArray[c] = dbHelper.GetBudgetRatio(spendingList.get(c).getRatioId()).getName();
            }

            // fill list
            ListView spendingListView = (ListView) findViewById(R.id.spendingList);
            SpendingListAdapter spendingListAdapter = new SpendingListAdapter(this, idArray, nameArray, amountArray, dateArray, categoryArray, ratioArray);
            spendingListView.setAdapter(spendingListAdapter);

            final Integer[] ID_ARRAY = idArray;
            spendingListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(ViewSpendings.this, EditCategory.class);
                    intent.putExtra("id", ID_ARRAY[position]);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }

    public void MoveToViewBudgetActivity(View view){
        Intent intent = new Intent(this, ViewBudgets.class);
        startActivity(intent);
        finish();
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

    public void MoveToAddSpendingActivity(View view){
        Intent intent = new Intent(this, AddNewSpending.class);
        startActivity(intent);
        finish();
    }
}

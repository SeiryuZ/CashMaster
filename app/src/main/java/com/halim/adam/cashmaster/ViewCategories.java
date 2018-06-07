package com.halim.adam.cashmaster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.halim.adam.cashmaster.ListAdapters.CategoryListAdapter;
import com.halim.adam.cashmaster.Objects.Category;

import java.util.ArrayList;

public class ViewCategories extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_categories);

        DatabaseHelper dbHelper = new DatabaseHelper(this);

        ArrayList<Category> categoryList = dbHelper.GetCategoryList();
        Integer[] idArray;
        String[] nameArray;

        if(categoryList != null) {
            idArray = new Integer[categoryList.size()];
            nameArray = new String[categoryList.size()];

            for (int c = 0; c < categoryList.size(); c++) {
                idArray[c] = categoryList.get(c).getId();
                nameArray[c] = categoryList.get(c).getName();
            }

            // fill list
            ListView categoryListView = (ListView) findViewById(R.id.categoryList);
            CategoryListAdapter categoryListAdapter = new CategoryListAdapter(this, idArray, nameArray);
            categoryListView.setAdapter(categoryListAdapter);

            final Integer[] ID_ARRAY = idArray;
            categoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(ViewCategories.this, EditCategory.class);
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

    public void MoveToAddCategoryActivity(View view){
        Intent intent = new Intent(this, AddNewCategory.class);
        startActivity(intent);
        finish();
    }

    public void MoveToStatisticsPage(View view) {
        Intent intent = new Intent(this, Statistics.class);
        startActivity(intent);
        finish();
    }
}

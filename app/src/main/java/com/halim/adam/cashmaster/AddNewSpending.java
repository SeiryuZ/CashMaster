package com.halim.adam.cashmaster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.halim.adam.cashmaster.Objects.Budget;
import com.halim.adam.cashmaster.Objects.Category;

import java.util.ArrayList;

public class AddNewSpending extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_spending);
        DatabaseHelper dbHelper = new DatabaseHelper(this);

        // Spinners
        Spinner spinnerCategory = findViewById(R.id.spinnerCategory);
        Spinner spinnerBudget = findViewById(R.id.spinnerBudget);

        // get data for spinner
        ArrayList<Category> categoryList = dbHelper.GetCategoryList();
        ArrayList<Budget> budgetList = dbHelper.GetBudgetList();

        // insert data to string arraylist
        ArrayList<String> categoryStrings = new ArrayList<>();
        ArrayList<String> budgetStrings = new ArrayList<>();
        for(int c = 0; c < categoryList.size(); c++){
            categoryStrings.add(categoryList.get(c).getName());
        }
        for(int c = 0; c < budgetList.size(); c++){
            budgetStrings.add(budgetList.get(c).getName());
        }

        ArrayAdapter categoryAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categoryStrings);
        ArrayAdapter budgetAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, budgetStrings);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        budgetAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerCategory.setAdapter(categoryAdapter);
        spinnerBudget.setAdapter(budgetAdapter);
    }

    public void GetInput(View view){

    }

    public void MoveToTransactionHistoryPage(View view){
        Intent intent = new Intent(this, TransactionHistory.class);
        startActivity(intent);
    }
}

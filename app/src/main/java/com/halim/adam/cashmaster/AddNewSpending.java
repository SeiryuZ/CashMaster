package com.halim.adam.cashmaster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.halim.adam.cashmaster.Objects.Budget;
import com.halim.adam.cashmaster.Objects.BudgetRatio;
import com.halim.adam.cashmaster.Objects.Category;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AddNewSpending extends Activity {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    Spinner spinnerCategory;
    Spinner spinnerBudget;
    ArrayList<Integer> categoryIdList = new ArrayList<>();
    ArrayList<Integer> budgetIdList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_spending);
        DatabaseHelper dbHelper = new DatabaseHelper(this);

        // Spinners
        spinnerCategory = findViewById(R.id.spinnerCategory);
        spinnerBudget = findViewById(R.id.spinnerBudget);

        // get data for spinner
        ArrayList<Category> categoryList = dbHelper.GetCategoryList();
        ArrayList<BudgetRatio> budgetRatioList = dbHelper.GetBudgetRatioList();

        // insert data to string arraylist
        ArrayList<String> categoryStrings = new ArrayList<>();
        ArrayList<String> budgetStrings = new ArrayList<>();
        for(int c = 0; c < categoryList.size(); c++){
            categoryIdList.add(categoryList.get(c).getId());
            categoryStrings.add(categoryList.get(c).getName());
        }
        for(int c = 0; c < budgetRatioList.size(); c++){
            budgetIdList.add(budgetRatioList.get(c).getId());
            budgetStrings.add(budgetRatioList.get(c).getName());
        }

        ArrayAdapter categoryAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categoryStrings);
        ArrayAdapter budgetAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, budgetStrings);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        budgetAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerCategory.setAdapter(categoryAdapter);
        spinnerBudget.setAdapter(budgetAdapter);
    }

    public void GetInput(View view) throws ParseException {
        EditText inputName = findViewById(R.id.inputName);
        EditText inputAmount = findViewById(R.id.inputAmount);
        EditText inputDate = findViewById(R.id.inputDate);

        DatabaseHelper dbHelper = new DatabaseHelper(this);

        // get data
        String name = inputName.getText().toString();
        Date date = null;
        if (!inputDate.getText().toString().matches("")){
            date = DATE_FORMAT.parse(inputDate.getText().toString());
        }
        Float amount = Float.parseFloat(inputAmount.getText().toString());
        Category category = dbHelper.GetCategory(categoryIdList.get(spinnerCategory.getSelectedItemPosition()));
        BudgetRatio budgetRatio = dbHelper.GetBudgetRatio(budgetIdList.get(spinnerBudget.getSelectedItemPosition()));

        // Insert
        if(date != null) {
            dbHelper.InsertSpending(name, amount, category.getId(), budgetRatio.getId(), date);
        }
        else{
            dbHelper.InsertSpending(name, amount, category.getId(), budgetRatio.getId());
        }

        // move to ViewSpendings
        Intent intent = new Intent(this, ViewSpendings.class);
        startActivity(intent);
        finish();
    }

    public void MoveToViewSpendingActivity(View view){
        Intent intent = new Intent(this, ViewSpendings.class);
        startActivity(intent);
        finish();
    }

    public void MoveToTransactionHistoryPage(View view){
        Intent intent = new Intent(this, TransactionHistory.class);
        startActivity(intent);
    }
}

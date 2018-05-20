package com.halim.adam.cashmaster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class AddNewBudgetRatio extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_budget_ratio);
    }

    public void GetInput(View view){
        EditText inputName = findViewById(R.id.inputName);
        EditText inputPortion = findViewById(R.id.inputPortion);

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        dbHelper.InsertBudgetRatio(inputName.getText().toString(), Float.valueOf(inputPortion.getText().toString()));

        Intent intent = new Intent(this, ViewBudgets.class);
        startActivity(intent);
        finish();
    }

    public void MoveToViewBudgetActivity(View view){
        Intent intent = new Intent(this, ViewBudgets.class);
        startActivity(intent);
        finish();
    }
}

package com.halim.adam.cashmaster;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddNewBudget extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_budget);
    }

    public void GetInput(View view){
        EditText inputName = findViewById(R.id.inputName);
        EditText inputPortion = findViewById(R.id.inputPortion);
        EditText inputAmount = findViewById(R.id.inputAmount);

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        dbHelper.InsertBudget(inputName.getText().toString(), Float.valueOf(inputPortion.getText().toString()), Float.valueOf(inputAmount.getText().toString()));
    }
}

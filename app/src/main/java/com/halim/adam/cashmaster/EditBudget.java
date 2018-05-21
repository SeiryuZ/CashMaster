package com.halim.adam.cashmaster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.halim.adam.cashmaster.Objects.BudgetRatio;

public class EditBudget extends Activity {
    BudgetRatio budgetRatio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_budget);

        DatabaseHelper dbHelper = new DatabaseHelper(this);

        TextView nameText = findViewById(R.id.nameText);
        TextView ratioText = findViewById(R.id.ratioText);

        // get extra
        int selectedId = getIntent().getIntExtra("id", 0);

        budgetRatio = dbHelper.GetBudgetRatio(selectedId);

        nameText.append(budgetRatio.getName());
        ratioText.append("Portion: " + budgetRatio.getRatio());
    }

    public void GetInput(View view){
        EditText nameInput = findViewById(R.id.nameInput);
        EditText ratioInput = findViewById(R.id.ratioInput);

        if(!nameInput.getText().toString().matches("")){
            budgetRatio.setName(nameInput.getText().toString());
        }
        if(!ratioInput.getText().toString().matches("")){
            budgetRatio.setRatio(Float.parseFloat(String.valueOf(ratioInput.getText())));
        }
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        dbHelper.UpdateBudgetRatio(budgetRatio);

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

package com.halim.adam.cashmaster;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.halim.adam.cashmaster.Objects.BudgetRatio;

public class EditBudget extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_budget);

        DatabaseHelper dbHelper = new DatabaseHelper(this);

        TextView nameText = findViewById(R.id.nameText);
        TextView ratioText = findViewById(R.id.ratioText);

        // get extra
        int selectedId = getIntent().getIntExtra("id", 0);

        BudgetRatio selectedBudgetRatio = dbHelper.GetBudgetRatio(selectedId);

        nameText.append(selectedBudgetRatio.getName());
        ratioText.append("" + selectedBudgetRatio.getRatio());
    }
}

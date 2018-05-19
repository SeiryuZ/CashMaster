package com.halim.adam.cashmaster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.halim.adam.cashmaster.Objects.Spending;

import java.text.ParseException;
import java.util.ArrayList;

public class ViewSpendings extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_spendings);

        DatabaseHelper dbHelper = new DatabaseHelper(this);

        TextView spendingText = findViewById(R.id.spendingText);
        ArrayList<Spending> spendingList = null;

        try {
            spendingList = dbHelper.GetSpendingList();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for(int c = 0; c < spendingList.size(); c++){
            spendingText.append(spendingList.get(c).getId() + ": " + spendingList.get(c).getName() + " " + spendingList.get(c).getDate() + " "
                    + spendingList.get(c).getAmount() + " " + spendingList.get(c).getCategoryId() + " " + dbHelper.GetBudgetRatio(spendingList.get(c).getRatioId()).getName() + "\n");
        }
    }

    public void MoveToTransactionHistoryPage(View view) {
        Intent intent = new Intent(this, TransactionHistory.class);
        startActivity(intent);
        finish();
    }
}

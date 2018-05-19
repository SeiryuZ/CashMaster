package com.halim.adam.cashmaster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.halim.adam.cashmaster.Objects.Spending;

import java.text.ParseException;

public class TransactionHistory extends Activity {
    TextView transactionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);
        transactionList = (TextView) findViewById(R.id.transactionList);

        DatabaseHelper dbHelper = new DatabaseHelper(this);

        Spending spending = null;
        try {
            spending = dbHelper.GetSpending(1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        transactionList.append(spending.getName());


    }

    public void MoveToNewSpendingActivity(View view){
        Intent intent = new Intent(this, AddNewSpending.class);
        startActivity(intent);
    }

    public void MoveToNewIncomePage(View view){
        Intent intent = new Intent(this, AddNewIncome.class);
        startActivity(intent);
    }
}

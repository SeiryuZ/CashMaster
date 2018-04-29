package com.halim.adam.cashmaster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TransactionHistory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);
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

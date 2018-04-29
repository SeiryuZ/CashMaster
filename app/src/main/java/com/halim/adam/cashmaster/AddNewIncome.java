package com.halim.adam.cashmaster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AddNewIncome extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_income);
    }

    public void MoveToTransactionHistoryPage(View view){
        Intent intent = new Intent(this, TransactionHistory.class);
        startActivity(intent);
    }
}

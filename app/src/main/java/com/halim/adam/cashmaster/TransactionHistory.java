package com.halim.adam.cashmaster;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.halim.adam.cashmaster.Objects.Category;

public class TransactionHistory extends Activity {
    TextView transactionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);
        transactionList = (TextView) findViewById(R.id.transactionList);

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        Category cat = dbHelper.GetCategory(3);
        transactionList.setText(cat.getName());
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

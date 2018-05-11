package com.halim.adam.cashmaster;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class TransactionHistory extends Activity {
    protected Cursor cursor;
    TextView transactionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);
        transactionList = (TextView) findViewById(R.id.transactionList);

        SQLiteDatabase db = new DatabaseHelper(this).getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM income", null);
        cursor.moveToFirst();
        if(cursor.getCount()<=0){
            transactionList.setText("Still Empty!");
        }
        else{
//            transactionList.setText(cursor.getString((cursor.getColumnIndex("name"))));
            for(int c = 0; c < cursor.getColumnCount(); c++){
                transactionList.append(cursor.getString(c).toString() + ", ");
            }
        }
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

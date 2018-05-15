package com.halim.adam.cashmaster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddNewIncome extends Activity {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_income);
    }

    public void MoveToTransactionHistoryPage(View view) throws ParseException {
        Intent intent = new Intent(this, TransactionHistory.class);
        startActivity(intent);

        EditText inputName = findViewById(R.id.inputName);
        EditText inputAmount = findViewById(R.id.inputAmount);
        EditText inputDate = findViewById(R.id.inputDate);
        Date date = new Date();

        DatabaseHelper dbHelper = new DatabaseHelper(this);

        if (!inputDate.getText().toString().matches("")){
            date = DATE_FORMAT.parse(inputDate.getText().toString());
        }
        dbHelper.InsertIncome(inputName.getText().toString(), Float.valueOf(inputAmount.getText().toString()), date);
    }
}

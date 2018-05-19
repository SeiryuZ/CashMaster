package com.halim.adam.cashmaster;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.halim.adam.cashmaster.Objects.Income;

import java.text.ParseException;
import java.util.ArrayList;

public class ViewIncomes extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_incomes);

        DatabaseHelper dbHelper = new DatabaseHelper(this);
    }
}

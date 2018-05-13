package com.halim.adam.cashmaster;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Statistics extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        TextView statisticsText = (TextView) findViewById(R.id.statisticsText);
    }
}

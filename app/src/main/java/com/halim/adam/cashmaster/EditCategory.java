package com.halim.adam.cashmaster;

import android.app.Activity;
import android.os.Bundle;

public class EditCategory extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_category);

        // get extra
        int selectedId = Integer.parseInt(getIntent().getStringExtra("id"));


    }
}

package com.halim.adam.cashmaster;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class AddNewCategory extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_category);
    }

    public void GetInput(View view){
        EditText addCategory = findViewById(R.id.inputName);

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        dbHelper.InsertCategory(addCategory.getText().toString());
    }
}

package com.halim.adam.cashmaster;

import android.app.Activity;
import android.content.Intent;
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

        Intent intent = new Intent(this, ViewCategories.class);
        startActivity(intent);
        finish();
    }

    public void MoveToViewCategoryActivity(View view){
        Intent intent = new Intent(this, ViewCategories.class);
        startActivity(intent);
        finish();
    }
}

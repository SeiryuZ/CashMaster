package com.halim.adam.cashmaster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.halim.adam.cashmaster.Objects.Category;

public class EditCategory extends Activity {
    Category category = new Category();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_category);

        TextView nameText = findViewById(R.id.nameText);

        DatabaseHelper dbHelper = new DatabaseHelper(this);

        // get extra
        int selectedId = getIntent().getIntExtra("id", 0);
        category = dbHelper.GetCategory(selectedId);

        nameText.append(category.getName());
    }

    public void GetInput(View view){
        EditText nameInput = findViewById(R.id.nameInput);

        category.setName(nameInput.getText().toString());
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        dbHelper.UpdateCategory(category);

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

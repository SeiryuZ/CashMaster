package com.halim.adam.cashmaster;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.halim.adam.cashmaster.Objects.Category;

import java.util.ArrayList;

public class ViewCategories extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_categories);

        DatabaseHelper dbHelper = new DatabaseHelper(this);

        TextView categoriesText = findViewById(R.id.categoriesText);

        ArrayList<Category> categoryList = dbHelper.GetCategoryList();

        for(int c = 0; c < categoryList.size(); c++){
            categoriesText.append(categoryList.get(c).getId() + ": " + categoryList.get(c).getName() + "\n");
        }
    }
}

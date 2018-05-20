package com.halim.adam.cashmaster.ListAdapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.halim.adam.cashmaster.R;

public class CategoryListAdapter extends ArrayAdapter {
    private final Activity context;
    private final Integer[] idArray;
    private final String[] nameArray;

    public CategoryListAdapter(Activity context, Integer[] idArray, String[] nameArray){

        super(context, R.layout.categories_list, nameArray);

        this.context = context;
        this.idArray = idArray;
        this.nameArray = nameArray;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.categories_list, null,true);

        //this code gets references to objects in the listview_row.xml file
        TextView nameTextField = (TextView) rowView.findViewById(R.id.nameText);

        //this code sets the values of the objects to values from the arrays
        nameTextField.setText(nameArray[position]);

        return rowView;

    }
}

package com.halim.adam.cashmaster.ListAdapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.halim.adam.cashmaster.R;

import java.util.Date;

public class IncomeListAdapter extends ArrayAdapter {
    private final Activity context;
    private final Integer[] idArray;
    private final String[] nameArray;
    private final Float[] amountArray;
    private final Date[] dateArray;

    public IncomeListAdapter (Activity context, Integer[] idArray, String[] nameArray, Float[] amountArray, Date[] dateArray){
        super(context, R.layout.categories_list, nameArray);

        this.context = context;
        this.idArray = idArray;
        this.nameArray = nameArray;
        this.amountArray = amountArray;
        this.dateArray = dateArray;
    }

    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.income_list, null,true);

        //this code gets references to objects in the listview_row.xml file
        TextView nameTextField = (TextView) rowView.findViewById(R.id.nameText);

        //this code sets the values of the objects to values from the arrays
        nameTextField.setText(nameArray[position]);

        return rowView;
    }
}

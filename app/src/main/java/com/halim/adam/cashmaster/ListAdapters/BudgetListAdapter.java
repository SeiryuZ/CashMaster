package com.halim.adam.cashmaster.ListAdapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.halim.adam.cashmaster.R;

public class BudgetListAdapter extends ArrayAdapter {
    private final Activity context;
    private final Integer[] idArray;
    private final String[] nameArray;
    private final Float[] ratioArray;
    private final Float[] amountArray;

    public BudgetListAdapter(Activity context, Integer[] idArray, String[] nameArray, Float[] ratioArray, Float[] amountArray){

        super(context, R.layout.budget_list, nameArray);

        this.context = context;
        this.idArray = idArray;
        this.nameArray = nameArray;
        this.ratioArray = ratioArray;
        this.amountArray = amountArray;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.budget_list, null,true);

        //this code gets references to objects in the listview_row.xml file
        TextView nameTextField = (TextView) rowView.findViewById(R.id.nameText);
        TextView ratioTextField = (TextView) rowView.findViewById(R.id.ratioText);
        TextView amountTextField = (TextView) rowView.findViewById(R.id.amountText);

        //this code sets the values of the objects to values from the arrays
        nameTextField.setText(nameArray[position]);
        ratioTextField.setText("Portion: " + ratioArray[position]);
        amountTextField.setText(String.format("%.0f", amountArray[position]));

        return rowView;

    }
}

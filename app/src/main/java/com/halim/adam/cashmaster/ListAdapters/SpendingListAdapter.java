package com.halim.adam.cashmaster.ListAdapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.halim.adam.cashmaster.DatabaseHelper;
import com.halim.adam.cashmaster.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SpendingListAdapter extends ArrayAdapter {
    private final Activity context;
    private final Integer[] idArray;
    private final String[] nameArray;
    private final Date[] dateArray;
    private final Float[] amountArray;
    private final String[] categoryArray;
    private final String[] ratioArray;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public SpendingListAdapter (Activity context, Integer[] idArray, String[] nameArray, Float[] amountArray, Date[] dateArray, String[] categoryArray, String[] ratioArray){
        super(context, R.layout.categories_list, nameArray);

        this.context = context;
        this.idArray = idArray;
        this.nameArray = nameArray;
        this.dateArray = dateArray;
        this.amountArray = amountArray;
        this.categoryArray = categoryArray;
        this.ratioArray = ratioArray;
    }

    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.spending_list, null,true);

        //this code gets references to objects in the listview_row.xml file
        TextView nameText = (TextView) rowView.findViewById(R.id.nameText);
        TextView amountText = (TextView) rowView.findViewById(R.id.amountText);
        TextView dateText = (TextView) rowView.findViewById(R.id.dateText);
        TextView budgetText = (TextView) rowView.findViewById(R.id.budgetText);
        TextView categoryText = (TextView) rowView.findViewById(R.id.categoryText);

        //this code sets the values of the objects to values from the arrays
        nameText.setText(nameArray[position]);
        amountText.setText("" + amountArray[position]);
        dateText.setText(DATE_FORMAT.format(dateArray[position]));
        budgetText.setText("Budget: " + ratioArray[position]);
        categoryText.setText("Category: " + categoryArray[position]);

        return rowView;
    }
}

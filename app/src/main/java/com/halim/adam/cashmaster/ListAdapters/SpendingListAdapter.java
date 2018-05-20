package com.halim.adam.cashmaster.ListAdapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.halim.adam.cashmaster.R;

import java.util.Date;

public class SpendingListAdapter extends ArrayAdapter {
    private final Activity context;
    private final Integer[] idArray;
    private final String[] nameArray;
    private final Date[] dateArray;
    private final Float[] amountArray;
    private final Integer[] categoryIdArray;
    private final Integer[] ratioIdArray;

    public SpendingListAdapter (Activity context, Integer[] idArray, String[] nameArray, Float[] amountArray, Date[] dateArray, Integer[] categoryIdArray, Integer[] ratioIdArray){
        super(context, R.layout.categories_list, nameArray);

        this.context = context;
        this.idArray = idArray;
        this.nameArray = nameArray;
        this.dateArray = dateArray;
        this.amountArray = amountArray;
        this.categoryIdArray = categoryIdArray;
        this.ratioIdArray = ratioIdArray;
    }

    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.spending_list, null,true);

        //this code gets references to objects in the listview_row.xml file
        TextView nameTextField = (TextView) rowView.findViewById(R.id.nameText);

        //this code sets the values of the objects to values from the arrays
        nameTextField.setText(nameArray[position]);

        return rowView;
    }
}

package ru.vsu.cs.farsharing;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CarListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] stateNumber;
    private final String[] brand;
    private final String[] model;
    private final String[] status;

    public CarListAdapter(Activity context, String[] stateNumber, String[] brand, String[] model, String[] status) {
        super(context, R.layout.car_list_item, stateNumber);
        this.context = context;
        this.stateNumber = stateNumber;
        this.brand = brand;
        this.model = model;
        this.status = status;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.car_list_item, null,true);

        TextView stateNumberView = (TextView) rowView.findViewById(R.id.stateNumber);
        TextView brandView = (TextView) rowView.findViewById(R.id.brandList);
        TextView modelView = (TextView) rowView.findViewById(R.id.modelList);
        TextView statusView = (TextView) rowView.findViewById(R.id.statusList);

        stateNumberView.setText(stateNumber[position]);
        brandView.setText(brand[position]);
        modelView.setText(model[position]);
        statusView.setText(status[position]);

        if (status[position].equals("Free"))
            statusView.setTextColor(Color.GREEN);
        else
            statusView.setTextColor(Color.RED);

        return rowView;
    };
}

package com.demo.customspinner;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner simplespinner, customspinner;
    String[] simplespinnerlist = {
            "Simple Spinner",
            "Cupcake",
            "Donut",
            "Eclair",
            "Froyo",
            "Gingerbread",
            "Ice Cream Sandwich",
            "Jelly Bean"
    };
    public ArrayList<SpinnerObject> CustomListViewValuesArr = new ArrayList<SpinnerObject>();
    CustomAdapter adapter;
    MainActivity activity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = this;

        simplespinner = (Spinner) findViewById(R.id.simplespinner);
        customspinner = (Spinner) findViewById(R.id.customspinner);

        SimpleSpinner(); // Call Simple Spinner Method

        CustomSpinner(); // Call Custom Spinner Method

    }

    private void SimpleSpinner(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, simplespinnerlist);
        simplespinner.setAdapter(adapter);
        simplespinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {

                        int position = simplespinner.getSelectedItemPosition();
                        Toast.makeText(getApplicationContext(), "You have selected " + simplespinnerlist[+position], Toast.LENGTH_LONG).show();
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub

                    }

                }
        );
    }

    private void CustomSpinner(){
        CustomListViewValuesArr.add(0,new SpinnerObject("Custom Spinner"));
        CustomListViewValuesArr.add(1,new SpinnerObject("Cupcake"));
        CustomListViewValuesArr.add(2,new SpinnerObject("Donut"));
        CustomListViewValuesArr.add(3,new SpinnerObject("Eclair"));
        CustomListViewValuesArr.add(4,new SpinnerObject("Froyo"));
        CustomListViewValuesArr.add(5,new SpinnerObject("Gingerbread"));
        CustomListViewValuesArr.add(6,new SpinnerObject("Honeycomb"));
        CustomListViewValuesArr.add(7,new SpinnerObject("Ice Cream Sandwich"));
        CustomListViewValuesArr.add(8,new SpinnerObject("Jelly Bean"));

        Resources res = getResources();
        adapter = new CustomAdapter(activity, R.layout.spinner_dropdown, CustomListViewValuesArr, res);
        customspinner.setAdapter(adapter);

        customspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    TextView txt = (TextView) view.findViewById(R.id.dropdown_txt);
                    String text = txt.getText().toString();
                    Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    // CustomSpinner Adapter
    public class CustomAdapter extends ArrayAdapter<String> {

        private Activity activity;
        private ArrayList data;
        public Resources res;
        SpinnerObject tempValues = null;
        LayoutInflater inflater;

        public CustomAdapter(
                MainActivity activitySpinner,
                int textViewResourceId,
                ArrayList objects,
                Resources resLocal
        ) {
            super(activitySpinner, textViewResourceId, objects);

            activity = activitySpinner;
            data = objects;
            res = resLocal;

            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = inflater.inflate(R.layout.spinner_dropdown, parent, false);
            tempValues = null;
            tempValues = (SpinnerObject) data.get(position);

            TextView txt = (TextView) row.findViewById(R.id.dropdown_txt);
            txt.setGravity(Gravity.CENTER);
            txt.setPadding(16, 16, 16, 16);
            txt.setTextSize(16);
            txt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_downarrow, 0);
            txt.setText(tempValues.getname());
            txt.setTextColor(Color.parseColor("#1171d0"));
            txt.setSingleLine(true);
            txt.setEllipsize(TextUtils.TruncateAt.END);
            txt.setSingleLine(true);

            return row;
        }

        public View getCustomView(int position, View convertView, ViewGroup parent) {

            View row = inflater.inflate(R.layout.spinner_dropdown, parent, false);
            tempValues = null;
            tempValues = (SpinnerObject) data.get(position);

            TextView txt = (TextView) row.findViewById(R.id.dropdown_txt);
            txt.setText(tempValues.getname());
            txt.setTextSize(17);
            txt.setPadding(0, 30, 0, 30);
            txt.setGravity(Gravity.CENTER);
            txt.setTextColor(Color.parseColor("#1171d0"));
            txt.setBackgroundColor(Color.parseColor("#FFFFFF"));

            return row;
        }
    }

    // CustomSpinner Setter-Getter
    public class SpinnerObject {

        private String name;

        public String getname() {
            return name;
        }

        public SpinnerObject(String name) {
            this.name = name;
        }

    }

}

package ru.vsu.cs.farsharing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private ListView carList;

    String[] stateNumber = {
            "A111AA",
            "A111AA",
            "A111AA",
            "A111AA",
            "A111AA",
            "A111AA",
    };
    String[] brand = {
            "LADA",
            "LADA",
            "LADA",
            "LADA",
            "LADA",
            "LADA",
    };
    String[] model = {
            "Priora",
            "Priora",
            "Priora",
            "Priora",
            "Priora",
            "Priora",
    };
    String[] status = {
            "Free",
            "Busy",
            "Free",
            "Busy",
            "Busy",
            "Free",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpViews();
        setUpListeners();
    }

    private void setUpViews() {
        CarListAdapter cla = new CarListAdapter(this, stateNumber, brand, model, status);
        carList = findViewById(R.id.carList);
        carList.setAdapter(cla);
    }

    private void setUpListeners() {
        carList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Click happened on position " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
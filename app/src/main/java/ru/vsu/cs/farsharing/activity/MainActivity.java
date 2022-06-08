package ru.vsu.cs.farsharing.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import ru.vsu.cs.farsharing.R;
import ru.vsu.cs.farsharing.activity.item.OnCarListItemListener;
import ru.vsu.cs.farsharing.model.entity.CarEntity;

public class MainActivity extends AppCompatActivity implements OnCarListItemListener {
    private RecyclerView carsRecyclerList;
    private CarEntity carsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpViews();
    }

    private void setUpViews() {

    }

    @Override
    public void onItemClick(int position) {
        //Intent toDetailedForecastData = new Intent(this, ForecastData.class);
        //startActivity(toDetailedForecastData);
    }
}
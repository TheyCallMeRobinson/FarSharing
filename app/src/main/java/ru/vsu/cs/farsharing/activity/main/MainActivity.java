package ru.vsu.cs.farsharing.activity.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.vsu.cs.farsharing.R;
import ru.vsu.cs.farsharing.activity.CarDetailsActivity;
import ru.vsu.cs.farsharing.model.response.BriefCarInfoResponse;

public class MainActivity extends AppCompatActivity implements OnCarListItemListener {
    private RecyclerView carsRecyclerList;
    private List<BriefCarInfoResponse> carsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void setUpViews() {
        carsRecyclerList = findViewById(R.id.carList);
        CarListAdapter carListAdapter = new CarListAdapter(this, carsList, this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        carsRecyclerList.setLayoutManager(manager);
        carsRecyclerList.setHasFixedSize(true);
        carsRecyclerList.setAdapter(carListAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(carsRecyclerList.getContext(), 1);
        carsRecyclerList.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void onItemClick(int position) {
        Intent toDetailedCarData = new Intent(this, CarDetailsActivity.class);
        startActivity(toDetailedCarData);
    }
}
package ru.vsu.cs.farsharing.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import ru.vsu.cs.farsharing.R;
import ru.vsu.cs.farsharing.activity.item.CarListAdapter;
import ru.vsu.cs.farsharing.activity.item.OnCarListItemListener;
import ru.vsu.cs.farsharing.config.AppConfig;
import ru.vsu.cs.farsharing.model.entity.CarEntity;
import ru.vsu.cs.farsharing.model.response.BriefCarInfoResponse;
import ru.vsu.cs.farsharing.service.CarService;

public class MainActivity extends AppCompatActivity implements OnCarListItemListener {
    private RecyclerView carsRecyclerList;
    private List<BriefCarInfoResponse> carsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new GetAllCars().execute();
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

    private class GetAllCars extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            Retrofit retrofit = AppConfig.retrofit;

            CarService carService = retrofit.create(CarService.class);
            Call<List<BriefCarInfoResponse>> call = carService.getCars();
            try {
                Response<List<BriefCarInfoResponse>> response = call.execute();
                carsList = response.body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            setUpViews();
        }
    }
}
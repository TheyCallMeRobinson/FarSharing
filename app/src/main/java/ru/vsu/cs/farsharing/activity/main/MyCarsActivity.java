package ru.vsu.cs.farsharing.activity.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.vsu.cs.farsharing.config.FarSharingApp;
import ru.vsu.cs.farsharing.databinding.ActivityMyCarsBinding;
import ru.vsu.cs.farsharing.model.entity.CarEntity;
import ru.vsu.cs.farsharing.service.app.GeocoderService;

public class MyCarsActivity extends AppCompatActivity implements OnCarListItemListener {
    private RecyclerView carsRecyclerList;
    private ActivityMyCarsBinding binding;
    private List<CarEntity> myCarsList;
    private TextView noCarsRentedField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyCarsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setUpViews();
        myCarsList = new ArrayList<>();
        FarSharingApp.getInstance().getClientService().getBookedCarsList(FarSharingApp.getInstance().getClientUid()).enqueue(new Callback<List<CarEntity>>() {
            @Override
            public void onResponse(@NonNull Call<List<CarEntity>> call, @NonNull Response<List<CarEntity>> response) {
                if (response.body() != null) {
                    myCarsList = response.body();
                    if (myCarsList.isEmpty()) {
                        noCarsRentedField.setVisibility(View.VISIBLE);
                    } else {
                        CarListAdapter carListAdapter = new CarListAdapter(MyCarsActivity.this, myCarsList, MyCarsActivity.this);
                        LinearLayoutManager manager = new LinearLayoutManager(FarSharingApp.getContext());
                        carsRecyclerList.setLayoutManager(manager);
                        carsRecyclerList.setHasFixedSize(true);
                        carsRecyclerList.setAdapter(carListAdapter);
                        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(carsRecyclerList.getContext(), 1);
                        carsRecyclerList.addItemDecoration(dividerItemDecoration);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<CarEntity>> call, @NonNull Throwable t) {
                t.printStackTrace();
                Snackbar.make(binding.getRoot(), "Не удалось получить данные от сервера", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void setUpViews() {
        carsRecyclerList = binding.myCarsList;
        noCarsRentedField = binding.noCarsRented;
    }

    @Override
    public void onItemClick(int position) {
        String street = GeocoderService.getStreetFromLocation(myCarsList.get(position).getLocation().getX(), myCarsList.get(position).getLocation().getY());
        Toast.makeText(this, "Авто находится на: " + street, Toast.LENGTH_LONG).show();
    }
}
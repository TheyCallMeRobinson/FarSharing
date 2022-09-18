package ru.vsu.cs.farsharing.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.vsu.cs.farsharing.activity.login.LoginActivity;
import ru.vsu.cs.farsharing.config.FarSharingApp;
import ru.vsu.cs.farsharing.databinding.ActivityMainBinding;
import ru.vsu.cs.farsharing.model.entity.CarEntity;

public class MainActivity extends AppCompatActivity implements OnCarListItemListener {
    private RecyclerView carsRecyclerList;
    private ActivityMainBinding binding;
    private List<CarEntity> carsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setUpViews();
        FarSharingApp.getInstance().getCarService().getCars().enqueue(new Callback<List<CarEntity>>() {
            @Override
            public void onResponse(@NonNull Call<List<CarEntity>> call, @NonNull Response<List<CarEntity>> response) {
                if (response.body() != null) {
                    carsList = response.body();
                    CarListAdapter carListAdapter = new CarListAdapter(MainActivity.this, carsList, MainActivity.this);
                    LinearLayoutManager manager = new LinearLayoutManager(FarSharingApp.getContext());
                    carsRecyclerList.setLayoutManager(manager);
                    carsRecyclerList.setHasFixedSize(true);
                    carsRecyclerList.setAdapter(carListAdapter);
                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(carsRecyclerList.getContext(), 1);
                    carsRecyclerList.addItemDecoration(dividerItemDecoration);
                } else {
                    Snackbar.make(binding.getRoot(), "Не удалось получить список автомобилей", Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<CarEntity>> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void setUpViews() {
        carsRecyclerList = binding.carList;
    }

    @Override
    public void onItemClick(int position) {
        if (FarSharingApp.getInstance().getClientUid() != null) {
            Intent toDetailedCarData = new Intent(this, CarDetailsActivity.class);
            toDetailedCarData.putExtra("carUid", carsList.get(position).getUid());
            startActivity(toDetailedCarData);
        } else {
            Snackbar.make(binding.getRoot(), "Подробная информация доступна только авторизованным пользователям", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Войти в аккаунт", v -> {
                        startActivity(new Intent(FarSharingApp.getContext(), LoginActivity.class));
                        finish();
                    }).show();
        }
    }
}
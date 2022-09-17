package ru.vsu.cs.farsharing.activity.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.vsu.cs.farsharing.activity.payment.PaymentActivity;
import ru.vsu.cs.farsharing.config.FarSharingApp;
import ru.vsu.cs.farsharing.databinding.ActivityCarDetailsBinding;
import ru.vsu.cs.farsharing.model.entity.CarEntity;
import ru.vsu.cs.farsharing.model.request.AddContractRequest;

public class CarDetailsActivity extends AppCompatActivity {

    private ActivityCarDetailsBinding binding;
    private Button confirmChoice;
    private TextView brand;
    private TextView model;
    private TextView bodyType;
    private TextView color;
    private TextView address;
    private TextView stateNumber;
    private TextView pricePerHour;
    private TextView rentForHours;
    private TextView total;
    private SeekBar seekBar;
    private CarEntity car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCarDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setUpViews();
        setCarData();
        setUpListeners();
    }

    private void setUpViews() {
        brand = binding.brand;
        model = binding.model;
        bodyType = binding.bodyType;
        color = binding.color;
        address = binding.address;
        rentForHours = binding.rentForHours;
        pricePerHour = binding.pricePerHour;
        stateNumber = binding.stateNumber;
        confirmChoice = binding.confirmChoice;
        total = binding.total;
        seekBar = binding.seekBar;
        seekBar.setVisibility(View.INVISIBLE);
        total.setVisibility(View.INVISIBLE);
        rentForHours.setVisibility(View.INVISIBLE);
    }

    private void setUpListeners() {
        confirmChoice.setOnClickListener(v -> {
            AddContractRequest request = new AddContractRequest(
                    FarSharingApp.getInstance().getClientUid(),
                    car.getUid(),
                    Instant.now().toString(),
                    Instant.now().plus(seekBar.getProgress(), ChronoUnit.HOURS).toString()
            );
            FarSharingApp.getInstance().getContractService().createNewContract(request).enqueue(new Callback<UUID>() {
                @Override
                public void onResponse(@NonNull Call<UUID> call, @NonNull Response<UUID> response) {
                    if (response.body() != null) {
                        Intent toPaymentActivity = new Intent(CarDetailsActivity.this, PaymentActivity.class);
                        UUID contractUid = response.body();
                        toPaymentActivity.putExtra("contractUid", contractUid);
                        toPaymentActivity.putExtra("carUid", car.getUid());
                        toPaymentActivity.putExtra("hours", seekBar.getProgress());
                        startActivity(toPaymentActivity);
                    } else {
                        Snackbar.make(binding.getRoot(), "Не удалось создать контракт", Snackbar.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<UUID> call, @NonNull Throwable t) {
                    t.printStackTrace();
                }
            });

        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String hours;
                switch(progress % 20) { // т.к. максимальное значение аренды 24 часа, то нам необязательно дополнительно проверять 11-14 часов, достаточно взять остаток от деления на 20
                    case 1:
                        hours = progress + " час";
                        break;
                    case 2:
                    case 3:
                    case 4:
                        hours = progress + " часа";
                        break;
                    default:
                        hours = progress + " часов";
                }
                String totalRubles = progress * car.getPricePerHour() + " рублей";
                rentForHours.setText(hours);
                total.setText(totalRubles);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    private void setCarData() {
        UUID carUid = (UUID) getIntent().getExtras().get("carUid");
        FarSharingApp.getInstance().getCarService().getCar(carUid).enqueue(new Callback<CarEntity>() {
            @Override
            public void onResponse(@NonNull Call<CarEntity> call, @NonNull Response<CarEntity> response) {
                if (response.body() != null) {
                    car = response.body();
                    brand.setText(car.getBrand());
                    model.setText(car.getModel());
                    bodyType.setText(car.getBodyType().getName());
                    color.setText(car.getColor().getName());
                    stateNumber.setText(car.getStateNumber());
                    address.setText(getStreetFromLocation(car.getLocation().getX(), car.getLocation().getY()));
                    pricePerHour.setText(String.valueOf(car.getPricePerHour()));
                    seekBar.setVisibility(View.VISIBLE);
                    total.setVisibility(View.VISIBLE);
                    rentForHours.setVisibility(View.VISIBLE);
                } else {
                    Snackbar.make(binding.getRoot(), "Не удалось связаться с сервером", Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<CarEntity> call, @NonNull Throwable t) {
                Snackbar.make(binding.getRoot(), "Не удалось связаться с сервером", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private String getStreetFromLocation(Double x, Double y) {
        Geocoder geocoder = new Geocoder(getBaseContext(), Locale.getDefault());
        String fullAddress = "";
        try {
            Address address = geocoder.getFromLocation(x, y, 100).get(0);
            fullAddress = address.getThoroughfare() + ", " + address.getSubThoroughfare() + ", " + address.getLocality();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fullAddress;
    }
}
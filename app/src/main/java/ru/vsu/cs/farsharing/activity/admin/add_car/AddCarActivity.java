package ru.vsu.cs.farsharing.activity.admin.add_car;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import lombok.var;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.vsu.cs.farsharing.config.FarSharingApp;
import ru.vsu.cs.farsharing.databinding.ActivityAddCarBinding;
import ru.vsu.cs.farsharing.model.request.AddCarRequest;

public class AddCarActivity extends AppCompatActivity {

    private EditText brand;
    private EditText model;
    private EditText color;
    private EditText stateNumber;
    private EditText body;
    private EditText pricePerHour;
    private EditText mileage;
    private EditText latitude;
    private EditText longitude;
    private Button addCar;
    private ActivityAddCarBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddCarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setUpViews();
        setUpListeners();
    }

    private void setUpViews() {
        brand = binding.brandField;
        model = binding.modelField;
        color = binding.colorField;
        stateNumber = binding.stateNumberField;
        body = binding.bodyTypeField;
        pricePerHour = binding.pricePerHourField;
        mileage = binding.mileageField;
        latitude = binding.latitudeField;
        longitude = binding.longitudeField;
        addCar = binding.addNewCarButton;
    }

    private void setUpListeners() {
        addCar.setOnClickListener(v -> {
            var addCarRequest = new AddCarRequest(
                    brand.getText().toString(),
                    model.getText().toString(),
                    color.getText().toString(),
                    stateNumber.getText().toString(),
                    body.getText().toString(),
                    Float.valueOf(pricePerHour.getText().toString()),
                    Float.valueOf(mileage.getText().toString()),
                    Float.valueOf(latitude.getText().toString()),
                    Float.valueOf(longitude.getText().toString())
            );
            FarSharingApp.getInstance().getCarService().postCar(addCarRequest).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                    if (response.code() == 200) {
                        clearAllFields();
                        Snackbar.make(binding.getRoot(), "Машина успешно добавлена", Snackbar.LENGTH_LONG).show();
                    } else {
                        Snackbar.make(binding.getRoot(), "Ошибка ввода", Snackbar.LENGTH_LONG).show();
                    }
                    clearAllFields();
                }

                @Override
                public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                    Snackbar.make(binding.getRoot(), "Не удалось связаться с сервером", Snackbar.LENGTH_LONG).show();
                    t.printStackTrace();
                }
            });
        });
    }

    private void clearAllFields() {
        brand.setText("");
        model.setText("");
        color.setText("");
        stateNumber.setText("");
        body.setText("");
        pricePerHour.setText("");
        mileage.setText("");
    }
}
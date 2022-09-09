package ru.vsu.cs.farsharing.activity.add_car;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ru.vsu.cs.farsharing.databinding.ActivityAddCarBinding;

public class AddCarActivity extends AppCompatActivity {

    private ActivityAddCarBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddCarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
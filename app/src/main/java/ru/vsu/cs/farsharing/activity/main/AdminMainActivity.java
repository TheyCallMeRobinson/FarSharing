package ru.vsu.cs.farsharing.activity.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ru.vsu.cs.farsharing.config.FarSharingApp;
import ru.vsu.cs.farsharing.databinding.ActivityAdminMainBinding;

public class AdminMainActivity extends AppCompatActivity {

    private ActivityAdminMainBinding binding;
    private Button addCar;
    private Button observeUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setUpViews();
        setUpListeners();
    }

    private void setUpViews() {
        addCar = binding.addCar;
        observeUsers = binding.observeUsers;
    }

    private void setUpListeners() {
        addCar.setOnClickListener(v -> {
//            Intent toAddCarList = new Intent(FarSharingApp.getContext(), AddCarActivity.class);
//            startActivity(toAddCarList);
        });
        observeUsers.setOnClickListener(v -> {
//            Intent toUserList = new Intent(FarSharingApp.getContext(), UserListActivity.class);
//            startActivity(toUserList);
        });
    }
}
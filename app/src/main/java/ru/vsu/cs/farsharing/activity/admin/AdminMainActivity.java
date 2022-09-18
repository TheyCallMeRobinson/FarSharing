package ru.vsu.cs.farsharing.activity.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

import ru.vsu.cs.farsharing.activity.admin.add_car.AddCarActivity;
import ru.vsu.cs.farsharing.activity.admin.client_list.ClientListActivity;
import ru.vsu.cs.farsharing.activity.welcome.WelcomeActivity;
import ru.vsu.cs.farsharing.config.FarSharingApp;
import ru.vsu.cs.farsharing.databinding.ActivityAdminMainBinding;

public class AdminMainActivity extends AppCompatActivity {

    private ActivityAdminMainBinding binding;
    private Button addCar;
    private Button observeUsers;
    private Button logout;

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
        logout = binding.logout;
    }

    private void setUpListeners() {
        addCar.setOnClickListener(v -> {
            Intent toAddCarList = new Intent(FarSharingApp.getContext(), AddCarActivity.class);
            startActivity(toAddCarList);
        });
        observeUsers.setOnClickListener(v -> {
            Intent toUserList = new Intent(FarSharingApp.getContext(), ClientListActivity.class);
            startActivity(toUserList);
        });
        logout.setOnClickListener(v -> {
            Snackbar dialog = Snackbar
                .make(binding.getRoot(), "Вы уверены, что хотите выйти?", Snackbar.LENGTH_INDEFINITE)
                .setAction("Да", view -> {
                    FarSharingApp.getInstance().clearUserSessionData();
                    startActivity(new Intent(FarSharingApp.getContext(), WelcomeActivity.class));
                    finish();
                });
            dialog.show();
        });
    }
}
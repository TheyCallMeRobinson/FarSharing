package ru.vsu.cs.farsharing.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

import ru.vsu.cs.farsharing.activity.main.MainActivity;
import ru.vsu.cs.farsharing.activity.main.MyCarsActivity;
import ru.vsu.cs.farsharing.activity.welcome.WelcomeActivity;
import ru.vsu.cs.farsharing.config.FarSharingApp;
import ru.vsu.cs.farsharing.databinding.ActivityMenuBinding;

public class MenuActivity extends AppCompatActivity {

    private ActivityMenuBinding binding;

    private Button account;
    private Button carList;
    private Button myCarsList;
    private Button reportBug;
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setUpViews();
        setUpListeners();
    }

    private void setUpViews() {
        account = binding.account;
        carList = binding.carList;
        myCarsList = binding.myCarsList;
        reportBug = binding.reportBug;
        logout = binding.logout;

        if (FarSharingApp.getInstance().getClientUid() == null) {
            account.setVisibility(View.GONE);
            myCarsList.setVisibility(View.GONE);
            logout.setVisibility(View.GONE);
        }
    }

    private void setUpListeners() {
        account.setOnClickListener(v -> startActivity(new Intent(FarSharingApp.getContext(), MyAccountActivity.class)));
        carList.setOnClickListener(v -> startActivity(new Intent(FarSharingApp.getContext(), MainActivity.class)));
        myCarsList.setOnClickListener(v -> startActivity(new Intent(FarSharingApp.getContext(), MyCarsActivity.class)));
        reportBug.setOnClickListener(v -> startActivity(new Intent(FarSharingApp.getContext(), TextSupportActivity.class)));
        logout.setOnClickListener(v ->  {
            Snackbar
                .make(binding.getRoot(), "Вы уверены, что хотите выйти?", Snackbar.LENGTH_INDEFINITE)
                .setAction("Да", view -> {
                    FarSharingApp.getInstance().clearUserSessionData();
                    startActivity(new Intent(FarSharingApp.getContext(), WelcomeActivity.class));
                    finish();
                }).show();
        });
    }
}
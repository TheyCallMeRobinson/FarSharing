package ru.vsu.cs.farsharing.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ru.vsu.cs.farsharing.activity.main.MainActivity;
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
        myCarsList.setOnClickListener(v -> startActivity(new Intent(FarSharingApp.getContext(), MainActivity.class)));
        reportBug.setOnClickListener(v -> startActivity(new Intent(FarSharingApp.getContext(), TextSupportActivity.class)));
        logout.setOnClickListener(v ->  {
            DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        FarSharingApp.getInstance().setClientUid(null);
                        FarSharingApp.getInstance().setUserUid(null);
                        startActivity(new Intent(FarSharingApp.getContext(), WelcomeActivity.class));
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(FarSharingApp.getContext());
            builder.setMessage("Вы уверены, что хотите выйти?")
                   .setPositiveButton("Да", dialogClickListener)
                   .setNegativeButton("Нет", dialogClickListener)
                   .show();
        });
    }

}